package com.example.demo;

import com.example.demo.domain.SocialType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, SocialType> {

    @Override
    public SocialType convert(String source) {
        return SocialType.valueOf(source.toUpperCase());
    }
}