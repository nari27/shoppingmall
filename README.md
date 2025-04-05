# 🌸 플로럴 (Floral) - 향수 쇼핑몰 프로젝트

## 🧴 프로젝트 소개

> “나만의 향기를 찾을 수 있는 공간, Floral”

**Floral**은 사용자가 다양한 향수 상품을 둘러보고, 구매자/판매자 역할로 자유롭게 활동할 수 있는 웹 기반 쇼핑몰입니다.  
Thymeleaf를 활용한 심플한 UI와, Spring Boot 기반의 견고한 백엔드 구조를 갖춘 풀스택 프로젝트입니다.

---
<img width="522" alt="향수쇼핑몰 메인" src="https://github.com/user-attachments/assets/f7283ebf-922d-46a1-a4e7-15f0a1fbb0b6" />

---

## 🛠️ 사용 기술

| 역할        | 사용 기술                      |
|-------------|-------------------------------|
| Frontend    | Thymeleaf (HTML, CSS, JS)     |
| Backend     | Spring Boot (MVC 패턴)        |
| Database    | MySQL                          |
| ORM/DB 연동 | Spring JDBC, MyBatis (어노테이션 기반) |

---

## ✨ 주요 기능

### 👤 회원 관리
- 회원가입, 로그인, 로그아웃
- 구매자 / 판매자 역할 분리
- 세션 기반 인증 관리

### 🧼 상품 관리
- 판매자: 상품 등록, 수정, 삭제
- 구매자: 상품 목록 및 상세 조회
- 이미지 등록 및 정적 리소스 관리

### 💬 자유게시판
- 익명 게시글 작성, 수정, 삭제
- 댓글 작성 기능
- 페이징 처리

---

## 📁 프로젝트 폴더 구조

> 실제 프로젝트 폴더 구성은 다음과 같습니다:

![폴더 구조](https://user-images.githubusercontent.com/your-username/your-image-id.png)

---

## 🖥️ 주요 화면

### 🔹 메인 페이지
![Floral 메인](https://user-images.githubusercontent.com/your-username/your-image-id2.png)

> 사용자는 향수를 탐색하고, 상품을 클릭해 상세 정보를 확인할 수 있습니다.

---

## ⚙️ 실행 방법

1. MySQL에서 데이터베이스 및 테이블 생성
2. `application.properties`에 DB 정보 설정
3. STS(Spring Tool Suite) 또는 IDE에서 실행
4. 웹 브라우저에서 `http://localhost:8080` 접속

---

## 📌 디렉토리 구성 설명

- `src/main/java`
  - `com.mall.controller` : 각 기능별 Controller
  - `com.mall.service` : Service 계층
  - `com.mall.dao` : DAO 및 MyBatis 매퍼
  - `com.mall.model` : VO / DTO 클래스
- `src/main/resources`
  - `mappers` : MyBatis XML 매퍼 파일
  - `static` : css, js, 이미지 등의 정적 자원
  - `templates` : HTML 템플릿 (Thymeleaf)

---

## 🧚‍♀️ 팀원

| 이름 | 역할 |  
|------|------|  
| 이나리 (Nari) | 기획, 프론트엔드, 백엔드, DB 설계 |

---

## 🗂️ 저장소 링크

🔗 [https://github.com/nari27/shoppingmall](https://github.com/nari27/shoppingmall)

---

## 💐 마무리 한 마디

이 프로젝트는 Spring Boot 기반의 웹 서비스 구조를 실습하고, 실제 서비스를 만들어보는 데에 중점을 둔 작업이었습니다.  
향후에는 결제 기능, 검색 필터, 관리자 기능 등으로 확장할 계획입니다! 😊
