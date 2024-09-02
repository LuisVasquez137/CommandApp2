plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.androidHilt)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.aplicacion.commandapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aplicacion.commandapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Esto es para lo de firestore y firebaseBom
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    //implementation("com.google.firebase:firebase-firestore-ktx")

    // para autenticacion de firebase
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics")
    //para conectarse por medio de google
    //implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Navigation compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-beta01")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    // implementation ("androidx.compose.material:material-icons-extended:$compose_version")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
    //implementation("androidx.compose.material:material:1.6.8")

    // Other dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    //para el splsash
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.35.1-alpha")

    //para la imagen de perfil
    //implementation("io.coil-kt:coil-compose:2.4.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}