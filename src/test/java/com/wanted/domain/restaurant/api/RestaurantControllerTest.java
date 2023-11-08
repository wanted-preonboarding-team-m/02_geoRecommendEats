package com.wanted.domain.restaurant.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.restaurant.application.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest extends AbstractRestDocsTests {

  private final static String url = "/api/v1/restaurants";
  @MockBean
  private RestaurantService restaurantService;

  private MultiValueMap<String, String> params;

  @BeforeEach
  void setUp() {
    // 기본 파라미터 설정
    params = new LinkedMultiValueMap<>();
    params.add("lat", "37.2040");
    params.add("lon", "127.07596008849987");
    params.add("range", "2.0");
    params.add("sort", "distance"); // 기본 정렬 기준을 '거리'로 설정
  }


  @Nested
  @DisplayName("맛집 조회 api 테스트")
  class getRestaurantsList {

    @Test
    @DisplayName("맛집 조회 api가 성공한다. 모든 필드 정상 + 정렬기준은 거리")
    @WithMockUser(roles = {"USER"})
    void 맛집_조회가_정상적으로_성공한다() throws Exception {
      mockMvc.perform(get(url).params(params))
          .andExpect(status().isOk());
    }

    @DisplayName("맛집 조회 api가 성공한다. 모든 필드 정상 + 정렬기준은 평점")
    @Test
    @WithMockUser(roles = {"USER"})
    void 맛집_조회_기준_평점_일때_성공한다() throws Exception {
      // 정렬 기준 평점
      params.set("sort", "rate");

      mockMvc.perform(get(url).params(params))
          .andExpect(status().isOk());
    }


    @DisplayName("맛집 조회 api가 성공한다. 모든 필드 정상 + 정렬기준은 입력안하면 거리입니다.")
    @Test
    @WithMockUser(roles = {"USER"})
    void 맛집_조회_기준_디폴트값_일때_성공한다() throws Exception {
      // 정렬 기준이 없어도 조회되는지 확인
      params.set("sort", "");

      mockMvc.perform(get(url).params(params))
          .andExpect(status().isOk());

    }

    @DisplayName("위도가 입력되지 않아 맛집 조회 api가 실패한다.")
    @Test
    @WithMockUser(roles = {"USER"})
    void 입력값이_없으면_맛집_조회가_실패한다() throws Exception {
      // 얕은 복사를 하여 새로운 맵을 생성
      MultiValueMap<String, String> paramsWithoutLat = new LinkedMultiValueMap<>(params);
      // lat 파라미터를 제거
      paramsWithoutLat.remove("lat");

      mockMvc.perform(get(url).params(paramsWithoutLat))
          .andExpect(status().is4xxClientError());
    }
  }

  @Nested
  @DisplayName("맛집 상세 정보 조회 테스트")
  class getRestaurantDetail {

    @Test
    @DisplayName("맛집 상세 정보 조회 api가 성공한다.")
    @WithMockUser(roles = {"USER"})
    void 맛집_상세_정보_조회가_성공한다() throws Exception {
      Long restaurantId = 1L;

      mockMvc.perform(
              get(url + "/" + restaurantId))
          .andExpect(status().isOk());
    }
  }
}