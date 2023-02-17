# ✨ 바나나공방 ✨


## 🎨 프로젝트 소개
<img src="https://user-images.githubusercontent.com/47595515/219480374-ea76abd7-2a0e-4ca3-9755-84eaee0034d7.png" width="500"/>

* 프로젝트명: 바나나공방
* 서비스 특징: 누구나 작가가 되어 자신의 작품을 공유할 수 있는 서비스
* 주요 기능
    - 회원 관리
    - 작품 업로드/다운로드
    - 작품 큐레이션
    - 작품 경매
* 주요 기술
    - WebRTC
    - JWT Authentication
    - REST API
* 프로젝트 진행 기간 : 2023.01.03일(화) ~ 2023.02.17(금)

<!-- 자유 양식 -->

## 💛 팀 소개
* 신선호: 팀장, 프론트엔드 개발
* 박시현: 부팀장, 프론트엔드 개발
* 김규리: 와이어프레임 설계, 프론트엔드 개발
* 박윤환: 백엔드 개발, DevOps 담당
* 박주희: 풀스택 개발
* 손유진: 백엔드 개발

<!-- 자유 양식 -->

## 📃 프로젝트 상세 설명

### ⚙ 개발 환경
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

### ⚙ 프로젝트 폴더 구조

- Frontend
  ```text
  ./src
  ├── _actions
  ├── _reducers
  ├── assets
  ├── components
  │   ├── Curations
  │   └── commons
  ├── hoc
  ├── models
  ├── routes
  │   ├── ArtsPage
  │   ├── CommissionsPage
  │   ├── CurationsPage
  │   ├── LandingPage
  │   ├── LayoutPage
  │   │   ├── Footer
  │   │   └── NavBar
  │   ├── LoginPage
  │   ├── MyPage
  │   └── SignUpPage
  └── testserver
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

### ⚙ 시스템 구성도
- 아키텍쳐
![프로젝트 아키텍쳐](https://user-images.githubusercontent.com/47595515/219516496-9b0b9e9b-ff5c-49db-9b80-1f4903164a35.png)

### ⚙ 기능 명세서
  <details>
    <summary>API</summary>
    <img src="https://user-images.githubusercontent.com/47595515/219517058-239bd9f2-12c0-4936-9e43-792ee83070f5.jpeg"/>
    
  </details>

### ⚙ ERD
![ERD](https://user-images.githubusercontent.com/47595515/219516499-b6bb9e37-c694-4314-8975-9e9d712fa063.png)


### ⚙ 컨벤션
- **git 컨벤션**
  ```text
  ### 제목
  # 커밋 타입: 작업내용 (제목과 본문은 한 줄 띄워주세요)
  
  
  ### 본문 - 한 줄에 최대 72 글자까지만 입력하기  
  # 무엇을, 왜, 어떻게 했는지
  
  
  # 꼬리말
  # (선택) 이슈번호 작성
  
  #   [커밋 타입]  리스트
  #   :sparkles:          : 기능 (새로운 기능)
  #   :bug:               : 버그 (버그 수정)
  #   :lipstick:          : CSS 등 사용자 UI 디자인 변경
  #   :recycle:           : 리팩토링
  #   :art:               : 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음)
  #   :memo:              : 문서 (문서 추가, 수정, 삭제)
  #   :white_check_mark:  : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)
  #   :hammer:            : 기타 변경사항 (빌드 스크립트 수정 등)
  #   :truck:             : 파일 혹은 폴더명을 수정하거나 옮기는 작업만 하는 경우
  #   :fire:              : 코드, 파일을 삭제하는 작업만 수행한 경우
  #   :twisted_rightwards_arrows:    : 브랜치 합병
  #   :rocket:            : 배포 관련
  # ------------------
  #   [체크리스트]
  #     제목 첫 글자는 대문자로 작성했나요?
  #     제목은 명령문으로 작성했나요?
  #     제목 끝에 마침표(.) 금지
  #     제목과 본문을 한 줄 띄워 분리하기
  #     본문에 여러줄의 메시지를 작성할 땐 "-"로 구분했나요?
  # ------------------
  ```
  
- **브랜치 전략**
  ```text
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
  - camelCase로 변수 작성하기
  - tab은 2 spaces

  - import 순서는 라이브러리/모듈 → 커스텀 객체 → 컴포넌트 → css파일
  - 클래스명
    - `kebab-case` 로 작성하기
    - `어떤페이지(컴포넌트)__무슨역할`


- **Backend 코드 컨벤션**
  - 명명법
    - 변수명, 메서드명은 카멜케이스
    - 의미없는 변수명 사용 지양 → 유지보수의 어려움
    - 메서드 이름은 소문자로 시작하고 동사로 작성 → ex) getName()
    - 클래스 이름은 대문자로 시작
    
  - 코딩 스타일 자동적용 설정
    - IntelliJ에 NAVER [캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/) 적용
    - 저장시 액션 설정
      ![sdfasdf](https://user-images.githubusercontent.com/47595515/219520513-12db4f55-b814-4395-9c01-6207abab589f.png)

### ⚙ 기능 상세 설명

