# 

<h1 align="center">키링(KeyLink)</h1>

<p align="center">
<a href="[https://kotlinlang.org](https://kotlinlang.org/)"><img alt="Kotlin Version" src="https://img.shields.io/badge/Kotlin-1.8.0-blueviolet.svg?style=flat"/></a>
<a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
<a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="AGP" src="https://img.shields.io/badge/AGP-7.4.2-orange?style=flat"/></a>
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg"/></a>
</p>

<br>

<p align="center">
💚 키링은 관심사가 비슷한 사람들과 손쉽게 소통할 수 있는 SNS 예요. <br>
💜 키워드를 설정하여 관심사에 대한 이야기와 아이디어를 공유하며, 다른 사용자와 연결될 수 있어요.
<br>
💚 키링을 통해 새로운 인연과 아이디어를 발견해보세요!
</p>

<br>

<p align="center">
<img src="https://github.com/mash-up-kr/ssam-d-Android/assets/51078673/24bb1ef9-7847-4765-8754-a5c501f481ad"/>
</p>

## App Download

<a href="https://play.google.com/store/apps/details?id=com.mashup.ssamd">
<img src="https://user-images.githubusercontent.com/63157395/211233100-2f255c00-3336-4125-b5da-2fd935e40b5a.png" width="150" />
</a>

## Development

### Android

- Minimum SDK level 24
- Target SDK level 33
- [Kotlin](https://kotlinlang.org/) 기반
- [Android View System](https://developer.android.com/guide/topics/ui/declaring-layout?hl=ko) + [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=ko) 기반 UI 설계
    - 100% Compose Migration 진행 중
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) 를 통한 비동기 처리
- Jetpack
    - [Compose](https://developer.android.com/jetpack/compose?hl=ko) - 선언형 UI 개발을 가능하게 하는 Android UI Kit
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - 구성 변경에도 UI 데이터를 보존할 수 있는 StateHolder를 제공하는 라이브러리
    - [Navigation](https://developer.android.com/guide/navigation) - 화면(with Composable) 간의 이동을 지원하는 라이브러리
    - [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - 대량의 데이터 로드 시, 네트워크 대역폭과 시스템 리소스를 효율적으로 사용할 수 있도록 Page 단위 로딩을 지원하는 라이브러리
    - [DataBinding](https://developer.android.com/topic/libraries/data-binding) for XML - 데이터를 레이아웃에 직접 바인딩 가능하게 함으로서 기존 `findViewById`를 대체하는 라이브러리
- [Hilt](https://dagger.dev/hilt/) - Dagger 기반의 Android 의존성 주입 라이브러리
- [Glide](https://bumptech.github.io/glide/) - 빠르고 효율적인 Android 이미지 로딩 라이브러리
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - REST API 통신을 구축하는 라이브러리
- [Kakao Auth SDK](https://developers.kakao.com/docs/latest/ko/kakaologin/android) - 카카오톡 및 카카오 계정 로그인을 제공하는 SDK
- Firebase
    - [Firebase Cloud Messsaging](https://firebase.google.com/docs/cloud-messaging/fcm-architecture?hl=ko) - 안정적으로 클라이언트 앱에 푸시 메시지를 전송할 수 있는 Firebase 메시징 솔루션

### Gradle

- Kotlin DSL과 buildSrc를 이용하여 종속성을 관리하고 있습니다.

## Architecture

키링(KeyLink)은 MVVM 기반의 클린 아키텍처 구조로 설계되어있습니다. <br>
Presentation, Domain, Data 3개의 레이어를 통해 객체들 간의 관심사 분리, 의존성 주입을 통해 레이어간의 의존성을 최소화합니다.

<img width = '800' src = 'https://github.com/mash-up-kr/ssam-d-Android/assets/51078673/78f5dbc1-66dd-446c-8258-dfbc57bae829'>
<br>
<br>

키링의 Presentation Layer는 안드로이드 권장 아키텍처의 UDF(단방향 데이터 흐름) 패턴을 사용합니다. <br>
UDF를 통해 UI에 상태를 표시하는 Composable과 상태 저장 및 변경을 요청하는 Composable을 완전히 분리하여 재사용성을 고려하고 있습니다.

<img width = '200' src = 'https://github.com/mash-up-kr/ssam-d-Android/assets/51078673/25a322ac-ef74-4de8-800f-1effc1d927ea'>


## License
```xml
MIT License

Copyright (c) 2023 Mash-Up SSAMD

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
