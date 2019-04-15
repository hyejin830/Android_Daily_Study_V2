
# Chapter01 안드로이드 스튜디오를 활용하는 데 필요한 기본기부터 응용까지

## 안드로이드 스튜디오
- IDE(Integrated Development Environmnet) :  통합 개발환경
- InteliJ IDEA 기반 (JetBrains사에서 개발한 IDE)
- 빌드 시스템 : 그레이들(Gradle)
- 화면 레이아웃 편입과 디버그, 성능 측정 기능

### 설정하기

- Application name 
 앱의 이름

- Company Domain
 앱의 패키지 이름 , 구글 플레이 상에서 중복에 허용되지 않으므로 같은 이름이 없는지 사전에 조사

- project Location
 프로젝트의 위치

### Min SDK 선택법

#### 안드로이드 버전 개요

 <img src="https://github.com/hyejin830/Android_Daily_Study_V2/blob/master/Day01/images/1.png" width="60%"></img>

- 안드로이드 버전 설정 시, 그 버전 이하의 안드로이드 단말기에서 앱을 설치 할 수 없다
- OS 버전 마다 이용할 수 있는 API가 다르다. 오래된 SDK를 유지하면서 새로운 API를 적용하려면 OS별로 따로 출시하거나 라이브러리를 이용하는 등 독자적으로 구현할 필요가 있다
- 앱을 효율적으로 개발하는데 이용할 수 있는 라이브러리가 몇 가지가 있다 예) Google Play Services : 구글 공식 라이브러리(API Level9부터 지원)
- OS별로 동작도 달리지므로 각 버전의 안드로이드마다 테스트하고 디버그해야 품질을 유지할 수 있다

#### Min SDK 어떻게 결정?

실제 안드로이드 버전의 이용 비율을 보고 개발할 때, 몇 %의 사용자를 대상으로 할지 결정하고, 그 수치를 바탕으로 Min SDK를 결정하는 방법을 권장

https://developer.android.com/about/dashboards/index.html?hl=en

##### kitkat으로 설정
- kitkat이상(version 19) 사용자 점유율 : 96.5%

### 안드로이드 스튜디오의 폴더 구성

#### 표시되는 구성

Android : 실제 폴더 구성과는 다르고 안드로이드 스튜디오가 보기 쉽게 정리해준 형태
Project : 실제 폴더 구성과 같다. 파일을 이동하거나 이름을 바꿀 때 여기서 작업하는 것이 좋다

#### 프로젝트

빌드 스크립트와 모듈

>build.gradle(프로젝트 빌드 설정 파일)

- 빌드 시스템의 설정 파일
- 프로젝트 전체 설정이 기술

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.3.2'
        
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

>settings.gradle(모듈의 설정 파일)

- 어느 디렉터리가 모듈인지 정의

```
include ':app'
```

>gradlew(그레이들의 래퍼 파일)

- 그레이들의 버전을 지정해서 빌드 가능

####모듈

- 프로젝트 안에는 모듈 단위의 디렉토리가 있고, 기본적으로 app이라는 이름의 모듈이 들어있다
- 새로운 모듈을 작성할 수 있다
- 보통 1개의 모듈로 앱을 개발하지만, 라이브러리 모듈을 만들고 여러 앱에서 공유하게 할 수 있다
- 모듈은 얼마든지 만들고 각각 의존관계를 기술 가능

>build.gradle(모듈의 빌드 설정 파일)

- 그레이들의 모듈 설정이 기술된 파일
- 앱의 버전 등 다양한 설정

```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.chapter01_0415"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}
```

>build디렉터리

- 빌드 시 생성되는 중간 생성물과 최종 산출물이 저장
- 안드로이드 애플리케이션 파일로서 최종적으로 apk파일이 만들어지는데, 이 파일은 build 디렉터리 아래에 저장

>src 디렉터리

- 소스코드나 이미지 등 리소스 파일
- 기본적으로 main 디렉터리 안에서 파일을 추가하고 편집하면서 개발을 진행
- androidTest와 test디렉터리 안에 테스트 코드가 들어간다

>main 디렉터리

- 빌드 대상이 되는 디렉터리

#### main 디렉터리 안의 내용

>java 디렉터리

- 앱의 소스코드인 자바 파일을 이곳에 기술

>res 디렉터리

- 앱에서 사용되는 이미지와 문자열 등의 리소스르 배치하는 곳
- drawble : 이미지 파일을 저장
- layout : 레이아웃의 XML 파일을 저장
- values : 문자열 등 파일을 저장
- Alternative Resource : 단말기 설정 상태에 특화된 리소스를 배치 가능, 다국어를 지원하거나 태블릿 화면을 지원하는 등 화면 밀도(dpi)별로 이미지를 준비

>AndroidManifest.xml

- 안드로이드 앱 설정을 기술하는 파일
- Activity와 Service 등과 같은 안드로이드 앱에서 사용되는 클래스 선언과 퍼미션 설정이 기술

-----------------

## 빠르게 하는 기능을 활용

### 개발 환경

#### Find Action

> command + shift + a

- 안드로이드 스튜디오의 거의 모든 기능에 접근 가능
- 단축키의 커맨드 표시
- 설정하고 싶은 항목을 찾고자 할 때

#### 코딩 환경

> Preference - Editor - General - Appearance

- show line numbers
- show method separators
- show whitespaces

### 단축키

코드를 작성할 때 높은 생산성을 보장 할 수 있다

> 기본 자동 완성

- Ctrl + Space
- 문자열을 바탕으로 예측하는 일반적인 자동 완성 기능

> 현재 위치의 자료형을 바탕으로 자동 완성

- Ctrl + Shift + Space
- 자료형을 이용한 자동 완성 기능

> 오류 수정

- Alt + Enter
- 임포트 
- 메서드 생성
- 인수 수를 늘리기 위한 활용

> 현재 구문 완성

- Command + Shift + Enter
- 현재 구문 완성
- 코드를 정렬하면서 세미콜론이나 {}를 입력해 주고 다음에 입력해야 하는 곳까지 이동

> 매개변수 정보 표시

- Command + P
- 메서드의 매개변수 목록을 보여준다

> 코드 자동 생성

- Command + N or Ctrl + Enter
- 생성자나 toString 메서드,, 접근자 메서드처럼 정형화된 코드를 생성

> 항목 추출

- Command + Alt +(V,F,M,C)
- V : Variable
- F : Field
- M : Method
- C : Const
- 커서가 있는 부분을 추출 할 수 있다


> postfix 자동 완성

- .notnull + enter
    - null을 체크하는 if문 생성

### 프로젝트 안을 자유롭게 이동

> 툴 윈도우 열기

- Command + 숫자

> 최근에 사용한 파일 열기

- Command + E
- 최근에 사용한 파일 목록을 표시

>통합 검색
- Shift + Shift
- 메서드명부터 파일명까지 모든 리소스를 대상으로 검색

>심볼 검색
- Command + Shift + O
- 심볼을 검색
- 메서드와 멤버 변수 등은 나오지만 XML 파일 등은 나오지 않는다

>선언부 열기

- Command + B
- 이 명령을 이용해 메서드의 선언부로 이동

> 메서드를 호출한 곳 열기

- Ctrl + Alt + H
- 메서드 선언에서 이용하면 호출한 곳의 목록을 열 수 있다

### 디버그 기능

- 한 줄 씩 진행가능
- 조건부 브레이크 포인트 생성 가능

---------

## 안드로이드 스튜디오 2.0 새로운 개발 환경

### 인스턴트 런

- 2.0부터 추가된 기능
- 빠르게 변경 사항을 애뮬레이터와 실제 디바이스에 반영하는 기능



