package com.wanted.domain.restaurant.openapi;

import com.wanted.domain.restaurant.openapi.dto.RestaurantOpenApiResponse;
import com.wanted.domain.restaurant.openapi.pipeline.RestaurantDataPipeline;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * 경기도 공공데이터 포털에서 가져온 맛집 데이터를 전처리를 거쳐서 DB에 저장한다.
 */
@RequiredArgsConstructor
public class RestaurantDataInitializer {

  private static final String CHINESE = "Genrestrtchifood";
  private static final String JAPANESE = "Genrestrtjpnfood";
  private static final String FAST_FOOD = "Genrestrtfastfood";

  // open api를 호출하여 데이터를 받아오기 위한 파이프라인
  private final RestaurantDataPipeline pipeline;

  /**
   * 정해진 타입들의 맛집 데이터들을 호출, 전처리, DB에 저장한다.
   */
  public void initRestaurantData() {
    initChineseData();
    initJapaneseData();
    initFastFoodData();
  }

  /**
   * 중식 데이터를 호출, 전처리, DB에 저장한다.
   */
  private void initChineseData() {
    // 중식 데이터 호출
    List<RestaurantOpenApiResponse> chineseRestaurantResponseList = pipeline.getRestaurantOpenApiData(
        CHINESE);

    // todo: 중식 데이터 전처리

    // todo: 중식 데이터 DB에 저장
  }

  /**
   * 일식 데이터를 호출, 전처리, DB에 저장한다.
   */
  private void initJapaneseData() {
    // 일식 데이터 호출
    List<RestaurantOpenApiResponse> japaneseRestaurantResponseList = pipeline.getRestaurantOpenApiData(
        JAPANESE);

    // todo: 일식 데이터 전처리

    // todo: 일식 데이터 DB에 저장
  }

  /**
   * 패스트푸드 데이터를 호출, 전처리, DB에 저장한다.
   */
  private void initFastFoodData() {
    // 패스트푸드 데이터 호출
    List<RestaurantOpenApiResponse> fastFoodRestaurantResponseList = pipeline.getRestaurantOpenApiData(
        FAST_FOOD);

    // todo: 패스트푸드 데이터 전처리

    // todo: 패스트푸드 데이터 DB에 저장
  }
}
