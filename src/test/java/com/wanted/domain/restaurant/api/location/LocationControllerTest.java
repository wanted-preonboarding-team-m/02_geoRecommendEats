package com.wanted.domain.restaurant.api.location;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.restaurant.application.location.LocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(LocationController.class)
class LocationControllerTest extends AbstractRestDocsTests {

    @MockBean
    private LocationService locationService;

    @DisplayName("시구군 데이터가 정상적으로 조회된다.")
    @Test
    @WithMockUser(roles = {"USER"})
    void 시구군_조회가_정상적으로_성공한다() throws Exception {

        mockMvc.perform(
                get("/api/v1/location"))
            .andExpect(status().isOk());
    }

}