# 🗓️ Schedule Manager API

> 일정 관리 + 댓글 기능이 포함된 Java/Spring 기반 CRUD API  
> 회원가입, 로그인, 일정 작성, 댓글 작성, 페이징 조회 등 필수/도전 기능 포함!

---

## 🚀 사용 기술

- Java 19
- Spring Boot 3.x
- Spring Web / JPA / Validation
- H2 Database
- Lombok
- Gradle
- Postman (API 테스트)

---

## 🧾 실행 방법

```bash
# 1. 프로젝트 클론
git clone https://github.com/bella0415/Schedule_App_advanced.git

# 2. IDE에서 열기 (IntelliJ 권장)
# 3. Gradle sync & 빌드

# 4. 실행
```

접속 경로:
- API 서버: `http://localhost:8080`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)

---

## 📌 주요 API 명세

### ✅ 회원가입 - [POST] /api/users/signup

요청

```json
{
  "username": "이슬",
  "email": "bella@test.com",
  "password": "1234"
}
```

응답  
```text
회원가입 성공
```

---

### ✅ 로그인 - [POST] /api/users/login

```json
{
  "email": "bella@test.com",
  "password": "1234"
}
```

- 로그인 성공 시 `JSESSIONID` 세션 쿠키 저장됨

---

### ✅ 일정 등록 - [POST] /api/schedules

```json
{
  "title": "과제하기",
  "content": "하기 싫어",
  "userId": 1
}
```

---

### ✅ 전체 일정 조회 - [GET] /api/schedules

응답 예시:

```json
[
  {
    "id": 1,
    "title": "과제하기",
    "content": "하기 싫어",
    "username": "이슬",
    "createdAt": "2025-04-03T12:00:00",
    "updatedAt": "2025-04-03T12:00:00"
  }
]
```

---

### ✅ 댓글 등록 - [POST] /api/comments

```json
{
  "content": "살려주세요",
  "userId": 1,
  "scheduleId": 1
}
```

---

### ✅ 댓글 수정 - [PUT] /api/comments/{id}

```text
수정된 댓글입니다!
```

---

### ✅ 댓글 삭제 - [DELETE] /api/comments/{id}

응답: `"댓글 삭제 성공"`

---

## 🧩 ERD (Entity Relationship Diagram)

간단한 관계 요약:

```
User (1) ⟶ (N) Schedule  
User (1) ⟶ (N) Comment  
Schedule (1) ⟶ (N) Comment
```

---

## 🔍 트러블슈팅 요약

- `Pageable` import 오류 → `java.awt.print.Pageable` ❌ → `org.springframework.data.domain.Pageable` ✅  
- `Schedule.getComments()` 호출 불가 → 단방향 연관관계라 직접 접근 안됨 → `CommentRepository.countByScheduleId(...)` 로 대체 시도 → 과제 범위 내에서는 중단

---

## 📁 폴더 구조

```
src
└── main
    ├── controller
    ├── domain
    │   ├── entity
    │   └── repository
    ├── dto
    ├── service
    └── config
```

---

## 🙋🏻‍♀️ API 테스트 방법

1. Postman 설치
2. 요청 보내기
   - `POST http://localhost:8080/api/users/signup`
   - `GET http://localhost:8080/api/schedules`
3. 응답 확인 & H2 Console 접속

---

## 💬 개발자 코멘트

- 이번 프로젝트를 통해 JPA의 단방향 연관관계 설계, 예외처리, 로그인 인증 흐름, DTO 패턴 등 학습함
- 도전 기능 중 일부(페이징 + 댓글 수)는 복잡성으로 인해 구현 중단하고 회고로 대체함
