package com.example.demo.presentation.interceptor;

import com.example.demo.domain.token.JwtTokenProvider;
import com.example.demo.exeption.ExpiredAccessTokenException;
import com.example.demo.presentation.support.AuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        validateToken(request);
        return true;
    }

    private void validateToken(final HttpServletRequest request) {
        final String token = AuthorizationExtractor.extract(request);
        if (jwtTokenProvider.inValidTokenUsage(token)) {
            throw new ExpiredAccessTokenException();
        }
    }
}
