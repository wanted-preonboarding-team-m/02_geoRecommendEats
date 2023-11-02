package com.wanted.domain.restaurant.openapi.dao;

import com.wanted.domain.restaurant.constant.FoodType;
import com.wanted.domain.restaurant.dao.employee.RestaurantEmployeeRepository;
import com.wanted.domain.restaurant.dao.facility.RestaurantFacilityRepository;
import com.wanted.domain.restaurant.dao.hygiene.RestaurantHygieneRepository;
import com.wanted.domain.restaurant.dao.RestaurantRepository;
import com.wanted.domain.restaurant.dao.site.RestaurantSiteRepository;
import com.wanted.domain.restaurant.dao.type.RestaurantTypeRepository;
import com.wanted.domain.restaurant.dao.workplace.RestaurantWorkplaceRepository;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.domain.restaurant.entity.employee.RestaurantEmployee;
import com.wanted.domain.restaurant.entity.facility.RestaurantFacility;
import com.wanted.domain.restaurant.entity.hygiene.RestaurantHygiene;
import com.wanted.domain.restaurant.entity.site.RestaurantSite;
import com.wanted.domain.restaurant.entity.type.RestaurantType;
import com.wanted.domain.restaurant.entity.workplace.RestaurantWorkplace;
import com.wanted.domain.restaurant.openapi.dto.RestaurantOpenApiData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 경기도 공공데이터 포털에서 가져온 맛집 데이터를 DB에 저장한다.
 */
@RequiredArgsConstructor
@Transactional
@Component
public class RestaurantDataDBInserter {

  private final RestaurantRepository restaurantRepository; // 맛집 리포지토리
  private final RestaurantEmployeeRepository employeeRepository; // 맛집 종업원 수 리포지토리
  private final RestaurantFacilityRepository facilityRepository; // 맛집 시설 리포지토리
  private final RestaurantHygieneRepository hygieneRepository; // 맛집 위생 리포지토리
  private final RestaurantSiteRepository siteRepository; // 맛집 소재지 리포지토리
  private final RestaurantTypeRepository typeRepository; // 맛집 타입 리포지토리
  private final RestaurantWorkplaceRepository workplaceRepository; // 맛집 사업장 리포지토리

  /**
   * 전처리된 맛집 데이터를 DB에 저장한다.
   *
   * @param restaurantOpenApiDataList 전처리된 맛집 데이터 리스트
   * @param type 맛집 타입
   */
  public void insertRestaurantData(List<RestaurantOpenApiData> restaurantOpenApiDataList, FoodType type) {
    // 전처리된 맛집 데이터를 저장할 리스트를 생성한다. (후에 saveAll로 한번에 저장하기 위함)
    List<Restaurant> restaurants = new ArrayList<>();
    List<RestaurantEmployee> employees = new ArrayList<>();
    List<RestaurantFacility> facilities = new ArrayList<>();
    List<RestaurantHygiene> hygienes = new ArrayList<>();
    List<RestaurantSite> sites = new ArrayList<>();
    List<RestaurantType> types = new ArrayList<>();
    List<RestaurantWorkplace> workplaces = new ArrayList<>();

    // 전처리된 맛집 데이터 리스트를 순회하며, DB에 저장한다.
    for (RestaurantOpenApiData restaurantOpenApiData : restaurantOpenApiDataList) {
      // 속성 테이블과 연관관계를 맺기 전의 Restaurant 객체를 생성한다.
      Restaurant restaurant = new Restaurant();

      // 맛집 속성 엔티티들을 리스트에 추가한다.
      employees.add(createEmployeeData(restaurantOpenApiData, restaurant)); // 맛집 종업원 수
      facilities.add(createFacilityData(restaurantOpenApiData, restaurant)); // 맛집 시설
      hygienes.add(createHygieneData(restaurantOpenApiData, restaurant)); // 맛집 위생
      sites.add(insertSiteData(restaurantOpenApiData, restaurant)); // 맛집 소재지
      types.add(createTypeData(type, restaurant)); // 맛집 타입
      workplaces.add(createWorkplaceData(restaurantOpenApiData, restaurant)); // 맛집 사업장

      // 속성 테이블과 연관관계 맺기를 끝낸 Restaurant 객체를 리스트에 추가한다.
      restaurants.add(restaurant);
    }

    // 맛집 엔티티 리스트를 한번에 저장한다. (save로 각각 저장하는 것보다 성능 좋음)
    restaurantRepository.saveAll(restaurants);
    employeeRepository.saveAll(employees);
    facilityRepository.saveAll(facilities);
    hygieneRepository.saveAll(hygienes);
    siteRepository.saveAll(sites);
    typeRepository.saveAll(types);
    workplaceRepository.saveAll(workplaces);
  }

  /**
   * 맛집의 종업원 수 엔티티를 생성한다.
   *
   * @param restaurantOpenApiData 맛집 데이터
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 종업원 수 엔티티
   */
  private RestaurantEmployee createEmployeeData(RestaurantOpenApiData restaurantOpenApiData, Restaurant restaurant) {
    return RestaurantEmployee.builder()
        .totalNumber(restaurantOpenApiData.getTotalNumber())
        .maleNumber(restaurantOpenApiData.getMaleNumber())
        .femaleNumber(restaurantOpenApiData.getFemaleNumber())
        .restaurant(restaurant)
        .build();
  }

  /**
   * 맛집 시설 엔티티를 생성한다.
   *
   * @param restaurantOpenApiData 맛집 데이터
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 시설 엔티티
   */
  private RestaurantFacility createFacilityData(RestaurantOpenApiData restaurantOpenApiData, Restaurant restaurant) {
    return RestaurantFacility.builder()
        .ratingName(restaurantOpenApiData.getRatingName())
        .waterFacilityName(restaurantOpenApiData.getWaterFacilityName())
        .restaurantAvailable(restaurantOpenApiData.getRestaurantAvailable())
        .facilityScale(restaurantOpenApiData.getFacilityScale())
        .madeYear(restaurantOpenApiData.getMadeYear())
        .closeDate(toLocalDate(restaurantOpenApiData.getCloseDate())) // String을 LocalDate로 변환하고 저장한다.
        .restaurant(restaurant)
        .build();
  }

  /**
   * 맛집 위생 엔티티를 생성한다.
   *
   * @param restaurantOpenApiData 맛집 데이터
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 위생 엔티티
   */
  private RestaurantHygiene createHygieneData(RestaurantOpenApiData restaurantOpenApiData, Restaurant restaurant) {
    return RestaurantHygiene.builder()
        .industryName(restaurantOpenApiData.getIndustryName())
        .businessName(restaurantOpenApiData.getBusinessName())
        .restaurant(restaurant)
        .build();
  }

  /**
   * 맛집 소재지 엔티티를 생성한다.
   *
   * @param restaurantOpenApiData 맛집 데이터
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 소재지 엔티티
   */
  private RestaurantSite insertSiteData(RestaurantOpenApiData restaurantOpenApiData, Restaurant restaurant) {
    return RestaurantSite.builder()
        .roadNameAddress(restaurantOpenApiData.getRoadNameAddress())
        .lotNumberAddress(restaurantOpenApiData.getLotNumberAddress())
        .zipCode(restaurantOpenApiData.getZipCode())
        .area(restaurantOpenApiData.getArea())
        .restaurant(restaurant)
        .build();
  }

  /**
   * 맛집 타입 엔티티를 생성한다.
   *
   * @param type 맛집 타입
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 타입 엔티티
   */
  private RestaurantType createTypeData(FoodType type, Restaurant restaurant) {
    return RestaurantType.builder()
        .type(type)
        .restaurant(restaurant)
        .build();
  }

  /**
   * 맛집 사업장 엔티티를 생성한다.
   *
   * @param restaurantOpenApiData 맛집 데이터
   * @param restaurant 맛집 객체
   * @return 생성된 맛집 사업장 엔티티
   */
  private RestaurantWorkplace createWorkplaceData(RestaurantOpenApiData restaurantOpenApiData, Restaurant restaurant) {
    // 사업장 엔티티를 생성한다.
    return RestaurantWorkplace.builder()
        .workplaceName(restaurantOpenApiData.getWorkplaceName())
        .licenseDate(toLocalDate(restaurantOpenApiData.getLicenseDate())) // String을 LocalDate로 변환하고 저장한다.
        .businessStatus(restaurantOpenApiData.getBusinessStatus())
        .classificationName(restaurantOpenApiData.getClassificationName())
        .restaurant(restaurant)
        .build();
  }

  /**
   * 다양한 날짜 형식의 String을 LocalDate 객체로 변환한다. (경기도에서 날짜 형식을 속성에 따라 분류한 것도 아니고 그냥 섞어 놓았음)
   * 사업자 인허가 일자, 폐업 일자에 사용된다.
   *
   * @param date 날짜 형식의 String (YYYYmmdd, YYYY-mm-dd 등등, 일수가 없는 데이터도 있음)
   * @return 변환된 LocalDate
   */
  private LocalDate toLocalDate(String date) {
    // String의 길이가 8이면 YYYYmmdd 형식
    if (date.length() == 8) {
      int year = Integer.parseInt(date.substring(0, 4));
      int month = Integer.parseInt(date.substring(4, 6));
      int day = Integer.parseInt(date.substring(6, 8));
      return LocalDate.of(year, month, day);
    }

    // String의 길이가 6이면 YYYYmm 형식
    if (date.length() == 6) {
      int year = Integer.parseInt(date.substring(0, 4));
      int month = Integer.parseInt(date.substring(4, 6));
      return LocalDate.of(year, month, 1);
    }

    // 그 외의 길이는 YYYY-mm-dd 형식
    String[] yearMonthDay = date.split("-");
    int year = Integer.parseInt(yearMonthDay[0]);
    int month = Integer.parseInt(yearMonthDay[1]);
    int day = Integer.parseInt(yearMonthDay[2]);
    return LocalDate.of(year, month, day);
  }
}
