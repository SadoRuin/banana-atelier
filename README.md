# 🍌🎨 바나나공방

![가로글씨X700](https://user-images.githubusercontent.com/109323665/220797982-4037bea6-db12-4eb3-9b6e-40acb7ad0ee1.png)

<br/>

### 🎞 기획 의도

- **바**로 **나**의 작품을 **나**눠봐요

- 창작가에게 작품을 어필할 공간 제공

- 소비자에게 다양한 방식으로 작가와 소통할 수 있는 창구 제공

<br/>

### 💡 서비스 특징

- 누구나 작가가 되어 자신의 작품을 공유

<br/>

### ✔ 주요 기능

- 이메일 인증을 통한 회원가입, 비밀번호 찾기
- 작품 업로드/다운로드
- 공지사항을 통해 작가의 일정 공유
- WebRTC를 이용한 작품 큐레이션
- WebRTC를 이용한 작품 경매(API 구현 완료)

<br/>

### 📅 프로젝트 진행 기간

2023.01.03일(화) ~ 2023.02.17(금)

<br/>

## 💛 팀 소개

- **신선호**: Frontend, 팀장
- **박시현**: Frontend, 부팀장
- **김규리**: Frontend, 와이어프레임 설계
- **박윤환**: Backend, DevOps 담당
- **박주희**: Backend, Frontend
- **손유진**: Backend

<br/>

## ⚙ 개발 환경

🔧 **Backend**

- IntelliJ : 2022.3.1 (Ultimate Edition)
- Java 11
- Spring Boot 2.7.8
- Spring Data JPA
- Spring Security
- JWT Authentication
- Swagger
- AWS S3
- MariaDB 10.10.2
- Redis 7.0.8

🔧 **Frontend**

- Visual Studio Code 1.75.1
- react 18.2.0
- styled-components 5.3.6
- redux 4.2.0
- yup 0.32.11

🔧 **Web RTC**

- OpenVidu 2.25.0

🔧 **CI/CD**

- AWS EC2 Ubuntu 20.04 LTS
- Docker 20.10.17
- Nginx 1.23.3
- Jenkins 2.375.2

<br/>

## 🗂 프로젝트 폴더 구조

- Frontend
  
  ```text
  ./src
  ├── _actions
  ├── _reducers
  ├── assets
  ├── components
  │   ├── Curations
  │   └── commons
  └── routes
      ├── ArtsPage
      ├── CommissionsPage
      ├── CurationsPage
      ├── LandingPage
      ├── LayoutPage
      │   ├── Footer
      │   └── NavBar
      ├── LoginPage
      ├── MyPage
      └── SignUpPage
  ```

- Backend
  
  ```text
  ./banana
  ├── api
  │   ├── controller
  │   └── service
  ├── config
  ├── db
  │   ├── entity
  │   │   └── enums
  │   └── repository
  ├── dto
  │   ├── enums
  │   ├── request
  │   └── response
  ├── exception
  ├── security
  │   └── jwt
  └── util
  ```

<br/>

## 🗺 서비스 아키텍처

<img src="https://user-images.githubusercontent.com/47595515/219516496-9b0b9e9b-ff5c-49db-9b80-1f4903164a35.png" width="600px">

<br/>

## 📜 기능 명세서

<details>
    <summary>API</summary>
    <img src="https://user-images.githubusercontent.com/47595515/219517058-239bd9f2-12c0-4936-9e43-792ee83070f5.jpeg" alt="Swagger 기능 명세서"/>

</details>

<br/>

## 📊 ERD

<img src="https://user-images.githubusercontent.com/47595515/219516499-b6bb9e37-c694-4314-8975-9e9d712fa063.png" width="600px">

<br/>

### 🤝 컨벤션

- **git 컨벤션**
  
  ```text
  ### 제목
  # 커밋 타입: 작업내용 (제목과 본문은 한 줄 띄워주세요)
  
  ### 본문 - 한 줄에 최대 72 글자까지만 입력하기
  # 무엇을, 왜, 어떻게 했는지
  
  # 꼬리말
  # (선택) 이슈번호 작성
  
  # [커밋 타입] 리스트
  # :sparkles: : 기능 (새로운 기능)
  # :bug: : 버그 (버그 수정)
  # :lipstick: : CSS 등 사용자 UI 디자인 변경
  # :recycle: : 리팩토링
  # :art: : 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음)
  # :memo: : 문서 (문서 추가, 수정, 삭제)
  # :white_check_mark: : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)
  # :hammer: : 기타 변경사항 (빌드 스크립트 수정 등)
  # :truck: : 파일 혹은 폴더명을 수정하거나 옮기는 작업만 하는 경우
  # :fire: : 코드, 파일을 삭제하는 작업만 수행한 경우
  # :twisted_rightwards_arrows: : 브랜치 합병
  # :rocket: : 배포 관련
  # ------------------
  # [체크리스트]
  # 제목 첫 글자는 대문자로 작성했나요?
  # 제목은 명령문으로 작성했나요?
  # 제목 끝에 마침표(.) 금지
  # 제목과 본문을 한 줄 띄워 분리하기
  # 본문에 여러줄의 메시지를 작성할 땐 "-"로 구분했나요?
  # ------------------
  ```

- **브랜치 전략**
  
  ```
  🌲 master
      - dev
        - dev-front
          - feature-front/기능명
        - dev-back
          - feature-back/기능명
        - fix : 문제가 생긴 브랜치에서 분기
          - fix-front/기능명
          - fix-back/기능명
      - docs/문서타입[ex) README, ppt]
  ```

- **Frontend 코드 컨벤션**
  
  - 변수명
    
    - `camelCase`
  
  - tab
    
    - `2 spaces`
  
  - import 순서
    
    - 라이브러리/모듈 → 커스텀 객체 → 컴포넌트 → css파일
  
  - 클래스명
    
    - `kebab-case` 로 작성하기
    - `어떤페이지(컴포넌트)__무슨역할`

- **Backend 코드 컨벤션**
  
  - 명명법
    
    - 변수명, 메서드명
      - `camelCase`
    - 의미없는 변수명 사용 지양 → 유지보수의 어려움
    - 메서드 이름은 소문자로 시작, 동사 → ex) getName()
    - 클래스 이름은 대문자로 시작
  
  - 코딩 스타일 자동적용 설정
    
    - IntelliJ에 NAVER [캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/) 적용
    
    - 저장시 액션 설정
      
      <img src="https://user-images.githubusercontent.com/47595515/219520513-12db4f55-b814-4395-9c01-6207abab589f.png" width="600px">

<br/>

## 🎨 기능 상세 설명

### 👉 회원가입

- 닉네임 중복 체크

- 이메일 중복 체크 및 인증메일 발송

- 인증번호 확인

- 위의 과정을 모두 수행해야 회원가입 가능

![회원가입](https://user-images.githubusercontent.com/109323665/220821218-9bb07775-0d65-47a7-9b60-0960e510add2.gif)
<br/><br/><br/>

### 👉 공지사항

- 작가의 공지사항을 통해 팬들이 작가의 일정 손쉽게 공유 가능

<img src="https://user-images.githubusercontent.com/109323665/220822010-26ac3422-e827-4696-b780-c1ef99610140.png" width="600px">

<br/><br/><br/>

### 👉 이미지 업로드

- 제목, 간단한 설명과 함께 작품 이미지를 업로드

- 첫번째 작품을 업로드함으로서 작가권한 획득

![작품등록](https://user-images.githubusercontent.com/109323665/220819816-41c17090-5183-40e8-80cc-332839b61bce.gif)
<br/><br/><br/>

### 👉 대표작품 설정

- 작가의 아뜰리에에 게시될 대표작품 설정 가능

![대표작품설정](https://user-images.githubusercontent.com/109323665/220819930-ec81939c-f04f-4f8e-9054-7bb35f705a58.gif)
<br/><br/><br/>

### 👉 큐레이션 등록

- 작가가 본인의 작품을 홍보하기 위해 큐레이션 등록
- 큐레이션에 사용할 작품 함께 등록
  - 경매를 위한 시작가와 호가 지정

![큐레이션 등록](https://user-images.githubusercontent.com/109323665/220821855-81082a04-1123-4b5e-a188-c0017d4476a9.gif)
<br/><br/><br/>

### 👉 큐레이션 진행

- 작가와 팬들의 webRTC를 이용한 소통
- 팬들은 자유롭게 작품의 설명 열람 가능
- 경매 희망 시 입찰 버튼을 통한 경매 참여

![큐레이션진행](https://user-images.githubusercontent.com/109323665/220820303-4a875a58-1d27-414d-8b3f-ae88e17e4888.gif)
<br/><br/><br/>

## 📢 Notion

프로젝트 진행 과정에서 필요한 회의, 공지, 일정 등을 원페이지 협업 툴인 노션을 통해 관리했습니다. <br/>
또한 컨벤션 규칙, 브랜치 활용 규칙 등을 노션에 명시해두었고, 팀 미팅에 대한 피드백과 질문을 기록해 두어 언제든 확인할 수 있도록 관리하고 있습니다.

<br/><br/>

## 👨‍👩‍👧 Scrum

매일 아침 10시에 팀 단위로 할 일을 10분 정도 공유했습니다.</br> 유연한 분위기에서 스크럼을 통해서 개발에 집중할 수 있는 팀 분위기를 만들었습니다.

<br/><br/>

## 💭 회고록

### 📄 기획

- 주어진 기한 내에 소화 가능하도록 구체적 일정 수립하기
- 빠르고 구체적인 기획을 통해 충분한 개발 시간 마련하기

### 📢 소통을 잘하자

- api 명세, 변수 수정 혹은 merge 를 할 때 (프론트엔드 ↔ 백엔드 소통 중요)
- 서로 맡은 부분과 진행률을 파악하기 위해 협업 툴을 잘 이용하기

### 📝 기록을 잘하자

- 스크럼이나 회의 등의 기록을 꼼꼼히 하기
- 오늘 할 일, 오늘 한 일, 해야할 일 등등 매일 기록하기
- 공부하면서 얻은 지식을 정리해서 공유하기 → 팀원 간 동일한 고민 방지
- 트러블 슈팅을 제대로 관리해서 시간 단축하기

### 📋 명세를 잘하자

- 스토리보드 → 요구사항 명세 → 기능명세 → api 명세 등
- 전체적인 흐름을 파악하기 위한 명세 필수