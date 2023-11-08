# 지리기반 맛집 추천 웹 서비스

![image](https://bow-hair-db3.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F571a24a3-05f9-4ea5-b01f-cba1a3ac070d%2F77d8ee9c-7271-46f6-b4ea-02fda08cccf4%2Flogo.png?table=block&id=a9a2ec57-b655-45e4-be7d-a370c4649007&spaceId=571a24a3-05f9-4ea5-b01f-cba1a3ac070d&width=2000&userId=&cache=v2)
## 목차

1. [개발 기간](#개발-기간)
2. [기술 스택](#기술-스택)
3. [프로젝트 개요](#프로젝트-개요)
4. [프로젝트 일정관리](#프로젝트-일정관리)
5. [구현 기능 목록](#구현-기능-목록)
6. [설계 및 의도](#설계-및-의도)
7. [ERD](#erd)
8. [구현 과정](#구현-과정)
9. [담당한 역할](#담당한-역할)
10. [API 명세](#api-명세)
11. [테스트](#테스트)
12. [TIL 및 회고](#TIL-및-회고)

## 개발 기간

2023-10-31 ~ 2023-11-09

## 기술 스택

<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="spring"/> <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="spring data jpa"/> <img src="https://img.shields.io/badge/querydsl-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="spring"/> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springSecurity&logoColor=white" alt="spring security"/> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="mysql"/>

## 코드 컨벤션

코드 컨벤션 관한 노션 정리

https://mizuirohoshi7.notion.site/b298797bef954741bd7ab33a047ba01a

## 프로젝트 개요
사용자 위치 기반 맛집을 추천하여 더 나은 다양한 음식 경험을 제공하기 위한 서비스입니다.

맛집에 대한 평가를 할 수 있어 더 나은 사용자 경험을 제공합니다.


공공데이터를 활용하여, 데이터 파이프라인을 구축했습니다. 스케쥴러를 도입하여 지역 음식점 목록을 자동으로 업데이트합니다.

자주 변경되지 않는 시구군(지역) 데이터는 csv파일을 업로드를 통해 구현했습니다.



## 프로젝트 일정관리

**Git Projects 사용**

https://github.com/orgs/wanted-preonboarding-team-m/projects/4/views/1


## 구현 기능 목록

* 유저
  * 회원가입
  * 로그인
  * 사용자 위치 설정
  * 사용자 정보 상세 조회


* 데이터파이프라인

  * 데이터 수집: Open API 연동 및 규격 분석
  * 데이터 전처리: 누락 값, 이상 값 및 표준화 처리
  * 데이터 저장: Raw 데이터 규격에 맞는 모델링
  * 자동화: 스케쥴러 도입 
  * csv 데이터 업로드


* 맛집
  * 시구군 목록 조회
  * 사용자 위치 기반 맛집 목록 조회
  * 맛집 상세 정보 조회
  * 맛집 평가

## 설계 및 의도
*작성예정*

## ERD

**Erd Cloud**

![image](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/assets/57309311/a34cfd22-2400-40c4-ad97-0bbf21b5d200)


## 구현 과정

1. [프로젝트 환경 설정](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/1)
    * application.yml 설정
    * P6Spy 설정
    * RestDocs 설정
    * Response Api Format 설정
    * 공통 예외 처리


2. 유저 기능 구현
   * [회원가입](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/13)
   * [로그인](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/16)
   * [사용자 위치 설정](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/28)
   * [사용자 정보 상세 조회](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/28)
   

3. [데이터파이프라인](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/4)
    * [스케쥴러 적용](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/26)
    * [csv 데이터 업로드](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/8)


4. 맛집 기능 구현
    * [시구군 목록 조회](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/19)
    * [사용자 위치 기반 맛집 목록 조회](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/21)
    * [맛집 상세정보 조회](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/25)
    * [맛집 평가](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/29)
   
## 담당한 역할

* [김정훈](https://github.com/jhva): csv 파일 업로드 구축
* [곽민성](https://github.com/kawkmin): 사용자 회원가입 및 로그인, 맛집 평가 구현
* [김선재](https://github.com/mizuirohoshi7): 데이터 파이프라인 구축
* [최소영](https://github.com/soyeong125): 프로젝트 환경 설정, 맛집 조회 기능 구현


* 코드 리뷰 및 리팩토링은 조원 모두가 함께 했습니다.

## API 명세

**Spring Rest Docs 기반 API 명세서**

https://wanted-preonboarding-team-m.github.io/02_geoRecommendEats/src/main/resources/static/index.html

![image](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/assets/57309311/d5034d8c-975a-4e2c-af30-059f4c486f56)
## 테스트

*작성예정*

## TIL 및 회고
* [하나의 객체를 여러 테이블로 분리한다면?](https://www.notion.so/mizuirohoshi7/ec334759b596410c871d9ea1a3ce47b5?pvs=4)
* [RestTemplate로 json을 객체로 자동 변환](https://www.notion.so/mizuirohoshi7/RestTemplate-json-9402434b740042498a7f748dfc5e78f5?pvs=4)
* [시큐리티의 비밀번호 검증은 언제 일어나는걸까](https://www.notion.so/mizuirohoshi7/2651c325e42b44bab3241164c956f45a?pvs=4)
* [CaseCade로 인해 JPA Delete가 작동 안한 이유](https://www.notion.so/mizuirohoshi7/CaseCade-JPA-Delete-b96e3dfd88e94526a4c8813e6854520e?pvs=4)
