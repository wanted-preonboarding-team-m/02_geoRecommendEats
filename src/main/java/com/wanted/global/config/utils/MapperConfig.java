package com.wanted.global.config.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MapperConfig {
    private final ObjectMapper mapper;

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }}
