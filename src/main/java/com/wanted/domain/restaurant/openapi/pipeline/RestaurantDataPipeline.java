package com.wanted.domain.restaurant.openapi.pipeline;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.domain.restaurant.openapi.dto.RestaurantOpenApiData;
import com.wanted.global.config.error.BusinessException;
import com.wanted.global.config.error.ErrorCode;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${open-api.key}")
  private static String API_KEY;

  // open api 호출시 사용할 restTemplate
  private final RestTemplate restTemplate;

  private final ObjectMapper mapper;

  private final JSONParser jsonParser = new JSONParser();

  /**
   * open api 호출 url 생성 후 받아온 json 데이터를 dto로 파싱하여 반환
   *
   * @param restaurantType 맛집 타입 (중식, 일식, 패스트푸드)
   * @return dto로 변환된 맛집 데이터 리스트
   */
  public List<RestaurantOpenApiData> getRestaurantOpenApiData(String restaurantType) {

    // open api 호출 url 생성
    URI url = createOpenApiUrl(restaurantType);

    // open api 호출 후 응답된 json 데이터를 String으로 받는다.
    String jsonString = restTemplate.getForObject(url, String.class);

    // JsonParser를 통해 jsonString을 jsonObject로 변환한다.
    JSONObject jsonObject = createJsonObject(jsonString);

    // 변환한 jsonObject에서 필요한 속성을 추출한다. (공공데이터 포털 json의 응답 형태 참고)
    JSONArray genre = (JSONArray) jsonObject.get(restaurantType);
    JSONObject row = (JSONObject) genre.get(1);
    JSONArray restaurantJsonList = (JSONArray) row.get("row");

    // 추출한 맛집 json 리스트를 dto 리스트로 변환한다.
    return createRestaurantOpenApiDataList(restaurantJsonList);
  }

  /**
   * open api 호출 url 생성
   *
   * @param restaurantType 맛집 타입 (중식, 일식, 패스트푸드)
   * @return open api 호출 url
   */
  private URI createOpenApiUrl(String restaurantType) {
    return UriComponentsBuilder.fromHttpUrl(BASE_URL + restaurantType)
        .queryParam("key", "ac1a55d500054c14a7cf20f19a069580")
        .queryParam("type", "json")
        .build()
        .toUri();
  }

  /**
   * jsonString을 jsonObject로 변환하여 속성명이나 인덱스로 값을 추출할 수 있게끔 준비한다.
   *
   * @param jsonString 변환할 json 문자열
   * @return 변환된 jsonObject
   */
  private JSONObject createJsonObject(String jsonString) {
    try {
      return  (JSONObject) jsonParser.parse(jsonString);
    } catch (ParseException e) {
      // jsonString을 jsonObject로 파싱하는 것이 실패하면 예외를 발생시킨다.
      throw new BusinessException(jsonString, "jsonString", ErrorCode.JSON_PARSE_ERROR);
    }
  }

  /**
   * 맛집 데이터가 담긴 json을 dto로 변환한다.
   *
   * @param restaurantJsonList 맛집 데이터가 담긴 json 리스트
   * @return dto로 변환된 맛집 데이터 리스트
   */
  private List<RestaurantOpenApiData> createRestaurantOpenApiDataList(JSONArray restaurantJsonList) {
    List<RestaurantOpenApiData> restaurantOpenApiDataList = new ArrayList<>();

    // 각각의 맛집의 json 데이터를 dto로 변환한다.
    for (Object restaurantJson : restaurantJsonList) {
      JSONObject jsonProperties = (JSONObject) restaurantJson;
      try {
        // json을 dto로 변환하여 리스트에 추가한다.
        restaurantOpenApiDataList.add(mapper.readValue(jsonProperties.toJSONString(), RestaurantOpenApiData.class));
      } catch (JsonProcessingException e) {
        // json을 dto로 변환하는 것이 실패하면 예외를 발생시킨다. (속성명이 다르거나, 타입이 다르거나 등)
        throw new BusinessException(jsonProperties.toJSONString(), "jsonString", ErrorCode.JSON_PARSE_ERROR);
      }
    }

    return restaurantOpenApiDataList;
  }
}
