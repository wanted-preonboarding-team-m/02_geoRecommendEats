package com.wanted.domain.restaurant.dto.restaurant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 맛집 조회 목록 응답 dto
 */
@Getter
public class RestaurantRecomResDto {
    private double lat;
    private double lon;
    private String sigun_name;
    private String sigun_code;
    private String type;
    private String workplcae_name;
    private String road_name_address;
    private String lot_number_address;
    private int zip_code;
    private double rate;
    private double distance;

    public RestaurantRecomResDto(double lat, double lon, String sigun_name, String sigun_code,
        String type, String workplcae_name, String road_name_address, String lot_number_address,
        int zip_code, double rate) {
        this.lat = lat;
        this.lon = lon;
        this.sigun_name = sigun_name;
        this.sigun_code = sigun_code;
        this.type = type;
        this.workplcae_name = workplcae_name;
        this.road_name_address = road_name_address;
        this.lot_number_address = lot_number_address;
        this.zip_code = zip_code;
        this.rate = rate;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
