package com.wanted.domain.restaurant.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.restaurant.application.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;


@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest extends AbstractRestDocsTests {

    @MockBean
    private RestaurantService restaurantService;

    @Nested
    @DisplayName("맛집 조회 api 테스트")
    class getRestaurantsList {

        @DisplayName("맛집 조회 api가 성공한다. 정렬기준은 거리")
        @Test
        @WithMockUser(roles = {"USER"})
        void 맛집_조회_거리() throws Exception {
            String lat = "37.2040";
            String lon = "127.07596008849987";
            Double range = 2.0;
            String sort = "distance";

            mockMvc.perform(
                    get("/api/v1/restaurants" + "?lat=" + lat + "&lon=" + lon + "&range=" + range
                        + "&sort=" + sort))
                .andExpect(status().isOk());
        }

        @DisplayName("맛집 조회 api가 성공한다. 정렬기준은 평점")
        @Test
        @WithMockUser(roles = {"USER"})
        void 맛집_조회_평점() throws Exception {
            String lat = "37.2040";
            String lon = "127.07596008849987";
            Double range = 2.0;
            String sort = "rate";

            mockMvc.perform(
                    get("/api/v1/restaurants" + "?lat=" + lat + "&lon=" + lon + "&range=" + range
                        + "&sort=" + sort))
                .andExpect(status().isOk());

        }

        @DisplayName("맛집 조회 api가 성공한다. 정렬기준은 입력안하면 거리입니다.")
        @Test
        @WithMockUser(roles = {"USER"})
        void 맛집_조회_디폴트는_거리() throws Exception {
            String lat = "37.2040";
            String lon = "127.07596008849987";
            Double range = 2.0;
            String sort = "";

            mockMvc.perform(
                    get("/api/v1/restaurants" + "?lat=" + lat + "&lon=" + lon + "&range=" + range
                        + "&sort=" + sort))
                .andExpect(status().isOk());

        }

        @DisplayName("맛집 조회 api가 실패한다.")
        @Test
        @WithMockUser(roles = {"USER"})
        void 맛집_조회_실패() throws Exception {
            String lon = "127.07596008849987";
            Double range = 2.0;
            String sort = "";

            mockMvc.perform(
                    get("/api/v1/restaurants" + "&lon=" + lon + "&range=" + range
                        + "&sort=" + sort))
                .andExpect(status().is4xxClientError());
        }
    }
}