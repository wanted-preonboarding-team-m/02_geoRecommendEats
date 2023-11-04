# 지리기반 맛집 추천 웹 서비스

![image](https://bow-hair-db3.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F571a24a3-05f9-4ea5-b01f-cba1a3ac070d%2F77d8ee9c-7271-46f6-b4ea-02fda08cccf4%2Flogo.png?table=block&id=a9a2ec57-b655-45e4-be7d-a370c4649007&spaceId=571a24a3-05f9-4ea5-b01f-cba1a3ac070d&width=2000&userId=&cache=v2)
## 목차

1. [개발 기간](#개발-기간)
2. [기술 스택](#기술-스택)
3. [프로젝트 개요](#프로젝트-개요)
4. [프로젝트 일정관리](#프로젝트-일정관리)
5. [구현 기능 목록](#구현-기능-목록)
6. [ERD](#erd)
7. [구현 과정](#구현-과정)
8. [담당한 역할](#담당한-역할)
9. [API 명세](#api-명세)
10. [테스트](#테스트)

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

## ERD

**Erd Cloud**

*작성예정*

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
   * 사용자 위치 설정
   * 사용자 정보 상세 조회
   

3. [데이터파이프라인](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/4)
    * [파이프라인 리팩토링](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/10[)
    * [csv 데이터 업로드](https://github.com/wanted-preonboarding-team-m/02_geoRecommendEats/issues/8)


4. 맛집
    * 시구군 목록 조회
    * 사용자 위치 기반 맛집 목록 조회
    * 맛집 상세정보 조회
    * 맛집 평가
   
## 담당한 역할

* [김정훈](https://github.com/jhva): csv 파일 업로드 구축
* [곽민성](https://github.com/kawkmin): 사용자 회원가입 및 로그인, 맛집 평가 구현
* [김선재](https://github.com/mizuirohoshi7): 데이터 파이프라인 구축
* [최소영](https://github.com/soyeong125): 프로젝트 환경 설정, 맛집 조회 기능 구현


* 코드 리뷰 및 리팩토링은 조원 모두가 함께 했습니다.

## API 명세

**Spring Rest Docs 기반 API 명세서**

*작성예정*

## 테스트

*작성예정*

