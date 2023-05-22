Tourmate
======================
---
# 1. 프로젝트 개요

**사용자**에게 한국의 다양한 관광지, 먹거리, 축제, 행사 등을 소개하여 지역 관광 활성화를 위한 지역 관광 소개 페이지를 구축하려고 한다.<br>
지역별 관광지 data를 분석하고 화면에 표시한다. 또한 여행계획을 위한 스케줄과 여행경로 공유 등 사용자 편의 기능을 구현하고 나만의 숨은 관광지를 소개하는 페이지와 자유롭게 토론이 가능한 게시판 등을 구현해 본다.

---
# 2. 프로젝트 요구사항

## 2.1. 구현된 기능
> ### SpringBoot & myBatis 이식
> - SpringBoot Framework로 변경
> - myBatis Framework로 변경

> ### User Service
> - 로그인
> - 사용자 정보 수정
> - 회원 탈퇴

> ### Tour Service
> - 관광지 검색 → 좋아요 수 우선순위 검색 사용
> - 관광지 상세 정보

## 2.2. 구현 예정 기능

> ### Plan Service
>- 플랜 담기
>- 플랜 저장
>- 플랜 게시판
>- 플랜 공유
>- 좋아요 및 찜 기능

> ### Admin Service
>- 회원 관리
>- 게시글 삭제

> ### Additional Service
> - 플랜 자동 생성 기능
>   - 플랜 관광지별 가중치에 따른 우선순위 작성, 거리 표시 등
> - 사용자 성향에 따른 관광지 추천
>   - 간단한 질의응답으로 사용자의 성향을 파악해 관광지를 추천
> - 날씨 기능 연동 (플랜 등)
> - 축제 정보를 가져와 플랜과 연동

---
# 3. 화면 구성

![image](/uploads/d8db7ed666fc0acbc1e5183fd64f5095/image.png)

---

# 4. API Docs

## 4.1. /tour

### @GET ( */tour/list?areaCode={nullable}&keyword={not null} )
```
 @RequestParam("areaCode") int areaCode<br>
 @RequestParam("keyword") String keyword
```

- 검색어에 따라 관광지 검색 결과를 반환하는 API
- 도시를 선택할 경우 특정 도시 내의 검색어 기반 결과 반환
- 좋아요 수를 기준으로 내림차순으로 반환

### @GET ( */tour/detail/{contentId} )
```
 @PathVariable(”contentId”) String contentId
```
- content_id에 해당하는 관광지의 안내 정보를 가져오는 API

## 4.2. /user

### @POST ( */user/login )
```
 @RequestBody {userId : String, userPw : String}
```
- 로그인 기능
- 유저 아이디와 비밀번호를 입력 받아 등록된 유저 여부를 확인
- 비밀번호는 해쉬로 암호화 하여 DB에 저장 (예정)

### @DELETE ( */user/logout )

- 서버 내 JWT 인증 해제 (예정)

### @GET ( */user/{userId} )
```
 @PathVariable("userId") String userId
```
- 유저 ID를 통해 유저 정보를 반환하는 API

### @PUT ( */user/modify )
```
 @RequestBody {userId : String, userPw : String, userName : String, phoneNumber : String, gender : 0 or 1
```
- userId와 일치하는 유저의 회원 정보를 수정하는 API
- userId는 변경할 수 없으며, 기존의 ID를 계속 사용한다

### @DELETE ( */user/remove )
```
 @RequestBody { userId : String }
```
- 회원 정보를 삭제하는 API
- 회원 탈퇴, 관리자의 회원 사용 정지 등에 사용

### @POST ( */user/regist )
```
@RequestBody {userId : String, userPw : String, userName : String, phoneNumber : String, gender : 0 or 1
```
- 신규 회원 등록 기능

--- 
# 5. 기능 구현 테스트
> **구현된 Service들 중 대표 기능의 테스트 화면**

## 5.1. User Service (/login)
![image](/uploads/cf22e873cea503bc924c503ebb3fee10/image.png)
![image](/uploads/58a8986c67a01f12504a1908659adce3/image.png)

## 5.2. Tour Service (/list)
![image](/uploads/17e6eea555fc4aa732e8b4f4fdc6baac/image.png)
![image](/uploads/fe12a83edac6236e7a9477268fdca85a/image.png)

---
# 6. DB설계
![image](/uploads/21baa6625a511897473fcfd0acf3418a/image.png)
