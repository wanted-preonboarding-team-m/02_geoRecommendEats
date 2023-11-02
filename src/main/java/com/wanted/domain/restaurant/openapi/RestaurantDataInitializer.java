package com.wanted.domain.restaurant.openapi;

import com.wanted.domain.restaurant.constant.FoodType;
import com.wanted.domain.restaurant.openapi.dao.RestaurantDataDBInserter;
import com.wanted.domain.restaurant.openapi.dto.RestaurantOpenApiData;
import com.wanted.domain.restaurant.openapi.pipeline.RestaurantDataPipeline;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 경기도 공공데이터 포털에서 가져온 맛집 데이터를 전처리를 거쳐서 DB에 저장한다.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class RestaurantDataInitializer {

  // open api를 호출하여 데이터를 받아오기 위한 파이프라인
  private final RestaurantDataPipeline pipeline;

  // 전처리된 데이터를 DB에 저장하기 위한 DBInserter
  private final RestaurantDataDBInserter dbInserter;

  /**
   * RestaurantType에 지정된 타입들의 맛집 데이터를 초기화 및 갱신합니다.
   * 반목문으로 enum에 지정된 타입들을 순회하기 때문에, 타입에 변화를 주고 싶으면 enum만 수정하면됩니다.
   */
  // todo: 스케쥴러로 관리
  public void initRestaurantData() {
    for (FoodType type : FoodType.values()) {
      log.info("맛집 데이터 초기화 시작: {}", type);
      initDataOfType(type);
    }
    log.info("맛집 데이터 초기화 완료");
  }

  /**
   * enum으로 지정한 타입의 맛집의 데이터를 호출, 전처리, DB에 저장합니다.
   *
   * @param type 맛집 타입 (중식, 일식, 패스트푸드)
   */
  private void initDataOfType(FoodType type) {
    // open api를 호출하여 데이터를 받아옵니다.
    List<RestaurantOpenApiData> restaurantOpenApiDataList = pipeline.getRestaurantOpenApiData(type.getUrlEndpoint());

    // 전처리된 데이터를 DB에 저장합니다.
    dbInserter.insertRestaurantData(restaurantOpenApiDataList, type);
  }
}
