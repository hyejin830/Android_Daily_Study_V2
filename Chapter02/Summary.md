# 액티비티

## 액티비티란?

- '활동'을 나타내는 것 처럼 액티비티는 전화를 걸고, 메일을 작성하고, 사진을 찍는 등 사용자가 어떤 활동을 할 때 실행되는 애플리케이션의 컴포넌트
- 액티비티에는 윈도우가 있고, 그 윈도우에 텍스트나 이미지를 표시해 사용자 조작에 반응

### AppCompatActivity 

- 액티비티를 상속하며, 액티비티를 상속함으로써 머티리얼 디자인의 가이드라인에 따른 AppCompat 라이브러리를 제대로 활용할 수 있다
- 상속할 수 없을 때 AppCompatDelegate를 이용 

## 액티비티의 수명주기

- 액티비티는 몇 가지 상태가 있고, 상태를 변경할 때 onCreate 등의 콜백이 안드로이드 `프레임워크`에서 호출

onCreate (생성 시) : 초기화 처리와 뷰 생성(setContentView) 등
onStart (비표시 시) : 통신이나 센서 처리를 시작
onRestart (비표 시) : 보통은 아무것도 하지 않아도 된다.
onResume (최전면 표시) : 필요한 애니메이션 실행 등의 화면 갱신 처리
onPause (일부 표시) : 애니메이션 등 화면 갱신 처리를 정지 또는 일시정지 할 때 `필요 없는 리소스를 해제`하거나, `필요한 데이터를 영속화`
onStop (비표시 시) : 통신이나 센서 처리를 정지
onDestroy(폐기 시) : 필요 없는 리소스를 해제, 액티비티 참조는 모두 정리

- 시스템이 모자란 경우 시스템이 onStop, onDestroy를 콜백하지 않고 액티비티를 강제 종료할 때가 있다
- 이러한 경우 데이터를 영속적으로 보존하려면 onPause에서 처리할 필요가 있다
- onCreate와 onDestroy, onStart와 onStop , onResume와 onPause 를 쌍으로 해서 어떤 시점에서 작업을 처리해야 할지 쉬워진다

ex)
onCreate에서 뷰를 만들면 onDestroy에서 해제
뷰는 액티비티가 폐기된 다음, 가비지 콜렉션(GC)에 의해 자동으로 메모리에서 해제

onStart에서 위치 정보 취득을 시작했다면
onStop에서 (만약 정보 취득을 완료하지 못했다면) 취득을 정지하는 식

- 단, 액티비티의 인스턴스가 다른 클래스에 참조되고 있을 때는 폐기된 이후에도 메모리에 남아 결국 메모리 누수가 발생한다

## 디바이스 설정의 갱신 탐지

- 디바이스 설정에 변경이 발생하면 기본적으로 시스템에서 현재 액티비티를 폐기하고 새로 생성
 ex) 화면을 세로에서 가로로 돌리거나, 언어 설정 변경, SIM 교체에 따른 전화번호 변경 등

- 액티비티를 재생성할 때, `현재 상태를 일시적으로 저장해서 이용`하고 싶은 경우가 있다. 
    - onSaveInstanceState/onRestoreInstanceState 콜백메서드를 이용
    - onSaveInstanceState로 상태를 저장 
    - onRestoreInstanceState로 상태를 복귀

- onSaveInstanceState() 메서드 인수로 전달되는 Bundle 형 인스턴스에 저장하고 싶은 데이터를 설정 할 수 있다.
    - 자바 기본형, 문자열
    - 리스트
    - Paracelabe형 : 작은 화물이라는 의미에 able(가능하다)라는 접미사를 붙혀 `짐으로서 운반할 수 있는 것`

- onSaveInstaceState()/onRestoreInstanceState()에서는 시스템의 임시 영역을 활용하고, 프로세스 간 통신으로 데이터를 주고 받는다.
    - Parcelable인터페이스 : 프로세스 간 통신에서는 서로의 자료형을 어떻게 주고받을지 정해 둘 필요가 있는데, 그 전달 방법이 이것으로 정의 되어있다.


 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter02/images/1.png" width="60%"></img>

1) 적용하지 않은 경우

 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter02/images/2.png" width="60%"></img>

2) 적용한 경우

 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter02/images/3.png" width="60%"></img>

## 액티비티의 백스택

- 새로운 액티비티가 시작되면 실행 중이던 액티비티는 백스택에 들어간다
- 태스크라는 그룹에 속한다

### 3가지

- 같은 앱에서 시작된 액티비티는 같은 백스택에 쌓인다
- taskAffinity의 속성에 따라 소속되는 테스크가 달라진다
- lauchMode에 따라 액티비티 생성의 여부, 새로운 태스크에 속하는 등 액티비티의 시작이 달라진다.

* taskAffinity : 태스크 친화성이라는 이름이지만, 대체로 태스크 이름으로 바꿔 읽는 것이 이해가 쉽다

## 2.2 뷰와 레이아웃

### 2.2.1 뷰(View)

- UI를 구성하는 바탕이 되는 컴포넌트
- 네모난 영역
- TextView, Button, EditText, ImageView, CheckBox
- 뷰를 생성하는 방법 
 1) XML로 기술하는 방법 : 유지 및 관리에 유리
 2) 자바 코드로 기술하는 방법

#### 크기
- 3가지 방법
- 최종적인 크기는 부모 레이아웃에 따라 결정

1) warp_content : 뷰를 표시하기 위한 크기 
2) match_parent : 부모 뷰와 같은 크기
3) 크기 지정

#### 패딩과 마진

패딩 영역 : 배경색 칠해진다 , 뷰에 포함
마진 영역 : 공백 , 뷰에 미포함 , 뷰 요소 사이의 거리

### 2.2.2 레이아웃

- 뷰를 `어떤 위치`에 `어떤 크기`로 표시할지 결정하는 것

#### LinearLayout

 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter02/images/4.png" width="60%"></img>

### 2.2.3 커스텀 뷰

- 기존 뷰를 조합해 커스텀 뷰 생성
- 단계
1. 커스텀 뷰의 레이아웃을 결정
2. 레이아웃 xml로 설정할 수 있는 항목을 attrs.xml에 기재
3. 커스텀 뷰 클래스를 생성
4. 메인 앱의 레이아웃에 삽입해서 확인

#### 1. 커스텀 뷰의 레이아웃을 결정
- 원하는 뷰 구성
- root tag가 merge : 불필요한 중첩을 줄이기 위해

#### 2. 레이아웃 xml로 설정할 수 있는 항목을 attrs.xml에 기재

#### 3. 커스텀 뷰 클래스를 생성
- LinearLayout 클래스 상속
- 주의해야 할 3가지
    1. 레이아웃 XML
    2. 스타일 반영
    3. 외부 클래스

 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter02/images/5.png" width="60%"></img>

#### 4. 메인 앱의 레이아웃에 삽입해서 확인

## 2.3 프래그먼트

- 액티비티 1개당 여러 개 존재
- 기능 단위로 프래그먼트로 나누어 구현 가능
- 프레임워크에 구현된 것, 지원 라이브러리에서 구현된 것 2가지 
- 단계
    1. 프래그먼트 클래스 생성
    2. 프래그먼트의 뷰 구축에 이용할 레이아웃 XML을 생성
    3. 액티비티로부터 작성한 프래그먼트를 이용

#### 1. 프래그먼트 클래스 생성
- Fragment 상속
- 기본 생성자

#### 2. 프래그먼트의 뷰 구축에 이용할 레이아웃 XML을 생성
- 레이아웃 XML을 전개하고 뷰를 생성
- 뷰를 생성하는 시점이 정해져 있어 onCreateView()에서 생성
- 뷰가 만들어지면 onViewCreated()가 콜백
- 액티비티에서 구현한 리스너와 프래그먼트의 연결 
    - 액티비티와 프래그먼트가 연결될 때 onAttach()
    - onAttach에서 액티비티에서 구현한 리스너가 구현되지 않았으면 에러
    - 액티비티에서 구현한 리스너를 프래그먼트에서 가질 수 있고, 이벤트가 발생한 시점에 액티비티에 알려줄 수 있다
    - 직접 참조 하지 않고 인터페이스로서 가지는 것으로 의존하지 않도록 결합을 느슨하게 한다

#### 3. 액티비티로부터 작성한 프래그먼트를 이용
- 프래그먼트 태그로 정적으로 생성 가능

### 2.3.2 수명주기

- onAttach() : 프래그먼트와 액티비티가 연결될 때, 이 시점에서 getActvity 메서드는 null을 반환
- onCreate() : 생성 시, 초기화 처리
- onCreateView() : 생성 시, 뷰 생성
- onActivityCreated() :생성 시, 초기화 처리, 뷰생성 등
- onStart() : 비표시 상태, 표시 전 시점
- onResume() : 표시 시, 필요한 애니메이션 등 실행 화면 갱신 처리
- onPause() : 일부 표시 상태, 애니메이션 드으 화면 갱신 처리 정지, 일시정지 시에 불필요한 리소스 해제, 필요한 데이터 영속화
- onStop() : 비표시 상태, 비표시된 시점
- onDestroyView() : 폐기 시, 필요 없는 리소스 해제
- onDestroy() : 폐기 시, 필요 없는 리소스 해제
- onDetach() : 폐기 시, 필요 없는 리소스 해제

### 2.3.3 동적 추가/삭제
- 프래그먼트를 추가할 컨테이너가 될 ViewGroup이 필요
- 알아둬야 할 3가지
    1. 프래그먼트 추가와 삭제는 트랜잭션 단위로 한다
    2. 프래그먼트 추가는 ViewGroup에 한다. 
    3. 액티비티와 마찬가지로 백스택이 존재
- 화면 회전 등 액티비티가 재생성될 경우, 액티비티와 마찬가지로 프래그먼트도 재생성
- 재생성할 때 초깃값 처리
    - Fragment.setArguments(Bundle)로 초기값을 설정하여 해결
    - 재생성 시, getArgments()를 호출해 설정한 값을 가져올 수 있다

### 2.3.4 중첩 프래그먼트

- 항상 동적으로 추가해야한다
- getChildFragmentManager()를 이용
- 백스택도 중첩되지 않은 프래그먼트처럼 사용할 수 있지만, [뒤로가기]키의 처리는 해주지 않는다
- 뒤로가기 키가 눌렸을 때, 부모 프래그먼트의 백스택으 확인하고 만약 백스택이 있다면 popBackStact() 할 필요가 있다

### 2.3.5 UI를 갖지 않는 프래그먼트
- 헤드리스 프래그먼트
- 네트워크 연결 확인 및 네크워크 연결 변경 감지를 구현
- setRetainInstance(true)로 설정 : 재생성할 필요가 없기 때문에
- 액티비티는 재생성되기 때문에 onCreate()에서 프래그먼트 생성에 대한 처리 필요
- ViewGroup의 레이아웃 id는 지정할 필요가 없다
- 네크워크 변경을 감지하고 통지하는데는 BroardcastReceiver와 LocalBroadcastManager를 이용


