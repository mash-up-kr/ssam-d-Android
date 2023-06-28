package dependencies

object Dep {
    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Activity {
            const val ktx = "androidx.activity:activity-ktx:1.2.3"
            const val compose = "androidx.activity:activity-compose:1.7.2"
        }

        object Fragment {
            const val ktx = "androidx.fragment:fragment-ktx:1.4.1"
        }

        object Lifecycle {
            private const val version = "2.6.1"
            const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val process = "androidx.lifecycle:lifecycle-process:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"

            object Compose {
                const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            }
        }

        object Room {
            private const val version = "2.5.1"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val testing = "androidx.room:room-testing:$version"
            const val jdbcDriver = "org.xerial:sqlite-jdbc:3.34.0"
        }

        object Core {
            const val core = "androidx.core:core-ktx:1.7.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.8.0"
    }

    object UnitTest {
        const val junit = "junit:junit:4.13.2"

    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
    }

    object Logger {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Dagger {
        object Hilt {
            const val version = "2.44"
            const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hilt = "com.google.dagger:hilt-android:$version"
            const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        }
    }


    object SquareUp {
        object OkHttp {
            private const val version = "4.10.0"
            const val okHttp = "com.squareup.okhttp3:okhttp:$version"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        }

        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
            const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        }

        object Moshi {
            private const val version = "1.14.0"
            const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
            const val moshiAdapter = "com.squareup.moshi:moshi-adapters:$version"
        }

        object LeakCanary {
            private const val version = "2.9.1"
            const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"
        }
    }

    object Kotlin {
        private const val version = "1.8.0"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

        object Coroutine {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Glide {
        private const val version = "4.13.2"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
        const val recyclerviewIntegration = "com.github.bumptech.glide:recyclerview-integration:$version"
        const val okHttpIntegration = "com.github.bumptech.glide:okhttp3-integration:$version"
    }

    object GlideCompose {
        private const val version = "1.0.0-alpha.1"
        const val glideCompose = "com.github.bumptech.glide:compose:$version"
    }

    object Navigation {
        private const val version = "2.5.3"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        const val compose = "androidx.navigation:navigation-compose:$version"
        const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }

    object Compose {
        private const val version = "1.4.3"
        const val ui = "androidx.compose.ui:ui:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material = "androidx.compose.material:material:$version"

        object Test {
            const val ui = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Debug {
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }
    }

    object Javax {
        const val inject = "javax.inject:javax.inject:1"
    }

    object Kakao {
        const val sdk = "com.kakao.sdk:v2-user:2.13.0"
    }

    object Airbnb {
        private const val lottieVersion = "6.0.0"
        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
        const val lottieCompose = "com.airbnb.android:lottie-compose:$lottieVersion"
    }

    object BlurView {
        private const val version = "2.0.3"
        const val blur = "com.github.Dimezis:BlurView:version-$version"
    }
}