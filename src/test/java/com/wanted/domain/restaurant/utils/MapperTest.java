package com.wanted.domain.restaurant.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.domain.restaurant.openapi.dto.location.response.LocationResponseDto;
import com.wanted.global.config.utils.MapperConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(MapperConfig.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private MapperConfig mapperConfig;
    @Test
    void 임시_DTO에_null_넣은_이후_체크()  {
        LocationResponseDto locationResponseDto = LocationResponseDto.builder()
                .lat(null)
                .lon(null)
                .sigun_code("시군코드")
                .sigun_name("시군이름").build();
        Assertions.assertThat(locationResponseDto.getLat()).isBlank();
        Assertions.assertThat(locationResponseDto.getLon()).isBlank();
    }
}
