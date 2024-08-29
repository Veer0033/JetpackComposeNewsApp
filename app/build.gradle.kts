plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ays.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ays.newsapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"8856eb7cb9494232ac999223fff80aaa\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
}

dependencies {

    // Android X Components
    implementation(NewsAppDependencies.coreKtx)
    implementation(NewsAppDependencies.appCompat)
    implementation(NewsAppDependencies.material)
    implementation(NewsAppDependencies.constraintLayout)
    implementation(NewsAppDependencies.fragmentKtx)
    implementation(NewsAppDependencies.loggingInterceptor)

    // Architectural Components
    implementation(NewsAppDependencies.lifecycleViewModel)

    // Room
    implementation(NewsAppDependencies.roomRuntime)
    kapt(NewsAppDependencies.roomCompiler)
    implementation(NewsAppDependencies.roomKtx)


    // Coroutines
    implementation(NewsAppDependencies.coroutinesCore)
    implementation(NewsAppDependencies.coroutinesAndroid)
    implementation(NewsAppDependencies.lifecycleRuntime)

    // Retrofit
    implementation(NewsAppDependencies.retrofit)
    implementation(NewsAppDependencies.converterGson)
    implementation(NewsAppDependencies.interceptor)


    // Navigation Components
    implementation(NewsAppDependencies.navigationFragment)
    implementation(NewsAppDependencies.navigationUi)

    // Dagger
    implementation(NewsAppDependencies.daggerHilt)
    kapt(NewsAppDependencies.hiltCompiler)
    implementation(NewsAppDependencies.hiltCommon)

    // Paging 3
    implementation(NewsAppDependencies.pagingRuntime)
    implementation(NewsAppDependencies.pagingCompose)

    // Compose
    implementation(NewsAppDependencies.activityCompose)
    implementation(platform(NewsAppDependencies.composeBom))
    androidTestImplementation(platform(NewsAppDependencies.composeBom))
    implementation(NewsAppDependencies.composeUi)
    implementation(NewsAppDependencies.composeUiGraphics)
    implementation(NewsAppDependencies.composeUiPreview)
    implementation(NewsAppDependencies.composeMaterial3)
    implementation(NewsAppDependencies.composeMaterial)
    implementation(NewsAppDependencies.coilCompose)
    implementation(NewsAppDependencies.composeLifecycle)
    implementation(NewsAppDependencies.hiltNavigationCompose)
    implementation(NewsAppDependencies.navigationCompose)
    implementation(NewsAppDependencies.lifecycleRuntime)
    androidTestImplementation(NewsAppDependencies.composeUiTest)
    debugImplementation(NewsAppDependencies.debugComposeUITooling)
    debugImplementation(NewsAppDependencies.debugComposeUItest)

    // Test
    testImplementation(NewsAppDependencies.junit)
    androidTestImplementation(NewsAppDependencies.extJunit)
    androidTestImplementation(NewsAppDependencies.espressoCore)
    testImplementation(NewsAppDependencies.mockito)
    testImplementation(NewsAppDependencies.coreTesting)
    testImplementation(NewsAppDependencies.coroutinesTest)
    testImplementation(NewsAppDependencies.turbine)

}