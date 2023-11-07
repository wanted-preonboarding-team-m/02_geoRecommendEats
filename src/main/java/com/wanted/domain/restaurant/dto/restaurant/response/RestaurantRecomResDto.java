package com.wanted.domain.restaurant.dto.restaurant.response;

import com.wanted.domain.restaurant.constant.FoodType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 맛집 조회 목록 응답 dto
 */
@Getter
public class RestaurantRecomResDto {
    //맛집 id
    private long id;

    //위도
    private double lat;

    //경도
    private double lon;

    //타입
    @Enumerated(EnumType.STRING)
    private FoodType type;

    //사업장명
    private String workplcaeName;

    //소재지 도로명 주소
    private String roadNameAddress;

    //소재지 지번 주소
    private String lotNumberAddress;

    //소재지 우편번호
    private int zipCode;

    //평점
    private double rate;

    //사용자 위치와 맛집 사이 거리
    private double distance;

    public RestaurantRecomResDto(long id, double lat, double lon, FoodType type, String workplcaeName, String roadNameAddress, String lotNumberAddress, int zipCode, double rate) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.workplcaeName = workplcaeName;
        this.roadNameAddress = roadNameAddress;
        this.lotNumberAddress = lotNumberAddress;
        this.zipCode = zipCode;
        this.rate = rate;
    }

    /**
     * 사용자 위치와 맛집 사이 거리 수정
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
