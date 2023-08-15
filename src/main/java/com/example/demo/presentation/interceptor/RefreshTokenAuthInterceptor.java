package com.example.demo.presentation.interceptor;

import com.example.demo.domain.token.JwtTokenProvider;
import com.example.demo.domain.token.RefreshToken;
import com.example.demo.domain.token.TokenRepository;
import com.example.demo.exeption.ExpiredRefreshTokenException;
import com.example.demo.exeption.InvalidRefreshTokenException;
import com.example.demo.exeption.NoRefreshTokenInCookieException;
import com.example.demo.exeption.RefreshTokenNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@RequiredArgsConstructor
public class RefreshTokenAuthInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        final String comparisonRefreshToken = extract(request);
        final Long memberId = jwtTokenProvider.getPayload(comparisonRefreshToken);
        final RefreshToken originalRefreshToken = tokenRepository.findByMemberId(memberId)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (originalRefreshToken.isDifferentFrom(comparisonRefreshToken)) {
            throw new InvalidRefreshTokenException();
        }
        if (jwtTokenProvider.inValidTokenUsage(comparisonRefreshToken)) {
            throw new ExpiredRefreshTokenException();
        }

        return true;
    }

    private String extract(final HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .orElseThrow(NoRefreshTokenInCookieException::new)
                .getValue();
    }
}
