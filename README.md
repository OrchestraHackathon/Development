# PROJECT NAME : 종강타임


## 🖥️ 프로젝트 소개
<p align="center"><img src="https://github.com/OrchestraHackathon/Development/assets/92447290/3650094d-5ec2-45e3-bf86-e36c040cc802" width="20%" height="20%"></p>
  
"**종강**은 새로운 학기의 **시작**입니다."   
'종강타임'은 과목을 만들고 시간표를 설정하여 효율적으로 방학 기간을 계획할 수 있는 어플리케이션입니다.

## 🧑‍🤝‍🧑 멤버 구성
+ `Android`
  + 박지원 : ji11won@naver.com
  + 이준영 : uhiccup@gmail.com
+ `iOS`
  + 정재연 : zzaibean@gmail.com
+ `Server`
  + 남보우 : nbo5548@naver.com
  + 이주언 : cacophony0609@gmail.com


## ⚙️ 개발 환경
+ `Language`
  + Android : Kotlin 1.8.0
  + iOS : Swift 5.8.1
  + Server : Java 20.0.2
+ `Framework`
  + Android : Android Studio
  + iOS : XCode
  + Server : Springboot
+ `Database : mysql`
+ `ORM : JPA`
+ `Figma`
  https://www.figma.com/file/qztCAfJU0YX28KeSYFxG0A/%EC%A2%85%EA%B0%95%ED%83%80%EC%9E%84?type=design&node-id=0-1&mode=design&t=sx6YTrzXyozdXT2j-0

## 📌 주요 기능 목차
1. 회원가입 & 로그인
2. 수강 과목   
    2-1 과목 둘러보기   
    2-2 과목 등록하기
3. 시간표 설정
4. 인기 과목 및 카테고리
5. 마이페이지
6. 학점 부여 및 수료증 발급


## 📋 기능 소개

### `회원가입`
+ `Android`
  + Retrofit
    + 네트워크 요청을 보다 간편하고 효과적으로 처리하기 위한 라이브러리로 REST API와 통신하여 데이터를 가져오거나 보내는 작업수행.


### `로그인`
+ `Server`
  + JWT를 사용하여 로그인 기능 구현.

 
### `수강 과목`
📌 과목 둘러보기
+ `Android`
  + 무한 스크롤
    + 데이터를 나눠서 수신받아 페이지 로딩 시간이 감소하여 사용자 친화적인 인터페이스 제공.

📌 과목 등록하기
+ `Server`
  + 사용자가 로그인할 때 받아온 JWT를 이용하여 따로 사용자의 정보 입력받지 않아도 회원 인증 가능.


### `시간표 설정`
+ `Android`
  + 오픈소스 : MinTimeTable(https://github.com/islandparadise14/MinTimetable)
    + 사용자 맞춤형으로 시간표 관리가 가능한 오픈소스를 이용.


### `인기 과목 및 카테고리`
+ `Android`
  + BaseActivity, BaseFragment
    + Activity와 Fragment에서 Data Binding을 선언하다보면 중복되는 보일러플레이트 코드를 제거하여, 구현시간을 단축시키고 코드 가독성을 높임.

      
### `마이페이지`


### `학점 부여 및 수료증 발급`


## 💡 기대효과
+ 자기주도적 학습 촉진
  + 사용자는 자신만의 과목을 설정함으로써 자신의 학습목표를 자기주도적으로 결정할 수 있다.
  + 학습의 동기 부여를 높이고, 개인의 학습 성취도를 향상시킨다.
+ 학습 경험 공유
  + 사용자들이 만든 과목을 공유하는 기능은 학습경험과 지식을 넓히는 기회를 제공한다.
  + 사용자 간의 상호작용을 통해 다양한 학습 경험을 공유하며, 그 과정에서 새로운 아이디어나 학습 전략을 얻을 수 있다.
+ 사용자 중심의 평가 시스템
  + 과목을 수료한 후 스스로 평가하는 시스템은 사용자에게 자기반성의 시간을 제공한다.
  + 학습성취도와 학습방법에 대한 깊은 이해를 얻을 수 있다.



