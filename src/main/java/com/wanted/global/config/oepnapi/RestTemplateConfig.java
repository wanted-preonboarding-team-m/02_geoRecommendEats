package com.wanted.global.config.oepnapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate을 Bean으로 등록
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
