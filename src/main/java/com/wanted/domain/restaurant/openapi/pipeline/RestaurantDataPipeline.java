package com.wanted.domain.restaurant.openapi.pipeline;

import com.wanted.domain.restaurant.openapi.dto.RestaurantOpenApiResponse;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 경기도 공공데이터 포털에서 open api를 호출하여 맛집 데이터를 받아오는 pipeline
 */
@Component
@RequiredArgsConstructor
public class RestaurantDataPipeline {

  // 경기도 공공데이터 포털에서 open api를 호출하는 url
  private static final String BASE_URL = "https://openapi.gg.go.kr/";

  // open api 호출시 파라미터에 필요한 인증 키
  @Value("${open-api-key}")
  private static String API_KEY;

  // open api 호출시 사용할 restTemplate
  private final RestTemplate restTemplate;

  /**
   * open api 호출 url 생성 후 받아온 json 데이터를 dto로 파싱하여 반환
   *
   * @param restaurantType 맛집 타입 (중식, 일식, 패스트푸드)
   * @return dto로 변환된 맛집 데이터 리스트
   */
  public List<RestaurantOpenApiResponse> getRestaurantOpenApiData(String restaurantType) {

    // open api 호출 url 생성
    URI url = UriComponentsBuilder.fromHttpUrl(BASE_URL + restaurantType)
        .queryParam("key", API_KEY)
        .build()
        .toUri();

    // open api 호출 후 응답된 json 데이터를 List<RestaurantOpenApiResponse>로 파싱하여 반환
    return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<RestaurantOpenApiResponse>>() {})
        .getBody();
  }
}
