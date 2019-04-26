
액티비티에는 화면을 표현하기 위한 비즈니스 로직, View 이벤트 수신, View에 데이터 반영 등 다양한 역할을 갖게 할 수 있다.
처리 내용이 많아지게 되면 결국에는 변경에 취약하고 관리가 힘든 `스파게티 코드`가 된다
이러한 문제를 대처할 수 있는 MVP, MVVM 등과 같은 설계 기법이 있다

## 일반적인 아키텍쳐 원칙

### Separation of concerns

- UI를 베이스로 한 클래스(액티비티, 프래그먼트)들은 UI를 다루거나 OS와 상호작용하는 로직을 포함해야 한다.
- 그래야 라이프싸이클과 관련된 많은 문제들을 피할 수 있다.

### Drive UI from a model

- Model : 앱을 위한 데이터를 다루는 것에 책임을 가진 컴포넌트
- View와 App 컴포넌트로부터 독립적이어야, 라이프 사이클과 관련된 것에 영향을 받지 않는다.
- 모델 클래스를 둠으로써, 앱은 테스트하기 용이해지고 일관성 있을 수 있다.

-----

### 1. MVP(Model View Presenter)
- 사용자 인터페이스를 구축할 때 이용하는 설계 기법

#### 1.1 설명

##### 1.1.1 Model 
- 데이터와 비즈니스 로직
- UI에 관한 로직은 가지지 않는다
- 데이터베이스나 API접근에 관한 처리

##### 1.1.2 View
- 데이터 표시
- 사용자의 액션은 프레젠터에 위임

##### 1.1.3 Presenter
- 모델과 뷰 사이에서 서로 통신
- 뷰에서 발생한 이벤트가 프레젠터에 알려지면 프레젠터는 그 이벤트에 대응하는 처리(모델에 접근)
- 뷰와 모델 사이에는 항상 프레젠터 
- 모델이나 뷰의 인터페이스 등을 이용해 접근 가능
- 테스트 시 목 객체(가짜 객체)로 대체 가능하여 테스트 용이

#### 1.2 장점
- 역할을 명확히 나누므로 코드의 관리 효율이 높아진다
- 뷰와 모델의 의존관계가 없어진다

#### 1.3 단점
- 인터페이스를 통해 뷰와 모델에 접근하므로 인터페이스로서 정의할 필요가 있기 때문에 코드가 길어지기 쉽다
- 모델에서 가져온 데이터를 뷰에 표시하는 것은 직접 구현

------

### 2. MVVM

- 안드로이드 그레이들 플러그인을 통해 데이터 바인딩이 지원
- 데이터바인딩 : 사용자 인터페이스와 데이터를 연결하는(바인딩하는) 메커니즘
- UI 로직을 분리 가능

#### 2.0 데이터 바인딩
- 레이아웃과 데이터를 연결

레이아웃 파일 - 생성된 클래스(연결) - 바인딩할 클래스
ex) main_activity.xml - MainActivityBinding(자동 생성) - Main.java

1) android:onClick="{@main::onClickLike}"

2) Main.java 의 onClickLike() 호출

3) likes(ObservableInt형).set(likes.get()+1);
 - 변경 사항 반영

#### 2.1 설명
##### 2.1.1 Model
- 데이터와 비즈니스 로직
    
##### 2.1.2 View
- 데이터를 표시
- 애니메이션이나 액티비티 전환

##### 2.1.3 ViewModel
- View의 상태와 UI에 관한 로직을 구현
- Model에서 가져온 데이터를 반영해서 표시
- ViewModel이 가진 값이 데이터 바인딩으로 자동적으로 뷰에 갱신 - 뷰 부분에서 반영하는 구현 코드 작성 불필요

#### 2.2 장점
- 액티비티를 작게 만들 수 있다
- 데이터 바인딩을 통해 모델에서 가져온 데이터를 뷰에 반영하는 로직도 작성할 필요가 없으므로 코드를 줄일 수 있다
- 뷰에 의존하는 코드가 없어 테스트 용이

#### 2.3 단점
- 바인딩에 대한 처리는 자동 생성되므로 데이터 바인딩 처리는 블랙박스화 되어있다
- 자동으로 생성된 코드는 일반적으로 가독성이 낮고 디버그하기 어렵다

#### 2.4. View - ViewModel 관계

##### 2.4.1 참조
View는 ViewModel에 대한 참조가 포함
그러나, ViewModel은 View에 대한 참조를 포함하지 않는다.

##### 2.4.2 커뮤니케이션
 - databinding
 - observer와 observable

##### 2.4.3 관계

###### 2.4.3.1 로직 분리
View는 UI에 관련된 로직
ViewModel은 실제 데이터가 처리되는 로직(View에 연결한 데이터와 명령으로 구성)

###### 2.4.3.2 과정
View에서 어떠한 액션이 있을 때 데이터 처리와 같은 것을 ViewModel에서 처리하고 처리가 끝난 후, UI가 변경되는 것은 `데이터바인딩`을 통해 뷰 변경

#### 2.5 ViewModel - Model 관계

 - ViewModel에서 모델을 통해 load한 데이터를 처리
 - ViewModel에서 Model을 통해 통신 작업 요청
    - Model에게 필요한 액션 요청을 하고 옵저빙하고 있다가 통신 완료 상태(onNext)가 되면 데이터 갱신 

## 3. 실습 예제

### 3.1 프로젝트 설명
- 깃허브의 API에 접근해 선택한 프로그래밍 언어의 프로젝트 리포 목록 로드
- 프로그래밍 언어 변경 가능, 변경 후 화면 갱신
- 리포 목록의 각 항목을 탭하면 상세 화면으로 이동

### 3.2 프로젝트에 필요한 라이브러리
- Glide
- Rtrofit
- Gson
- OkHttp
- Rxjava

### 3.3 코드

- [flat code](https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter05/Chapter05_Project)
- [MVP code](https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter05/Chapter05_Project_MVVM)
- [MVVM code](https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Chapter05/Chapter05_Project_MVVM)

[출처]
https://developer.android.com/jetpack/docs/guide?hl=en#common-principles
https://medium.com/@jsuch2362/android-%EC%97%90%EC%84%9C-mvvm-%EC%9C%BC%EB%A1%9C-%EA%B8%B4-%EC%97%AC%EC%A0%95%EC%9D%84-82494151f312
