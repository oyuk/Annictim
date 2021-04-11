import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    object Version {
        const val kotlin = "1.4.32"
        const val targetSdkVersion = "30"


        const val coroutinesVersion = "1.4.2"
    }

    object Dagger {
        private const val Version = "2.31.2"
        const val annotation = "javax.annotation:jsr250-api:1.0"
        const val dagger = "com.google.dagger:dagger:${Version}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Version}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version}"
    }

    object Retrofit {
        private const val Version = "2.8.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Version}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.8.0"
    }
    
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverterGson)
    implementation(Dependencies.Retrofit.loggingInterceptor)
}

fun DependencyHandler.dagger() {
    compileOnly(Dependencies.Dagger.annotation)
    implementation(Dependencies.Dagger.dagger)
    implementation(Dependencies.Dagger.daggerAndroid)
    implementation(Dependencies.Dagger.daggerAndroidSupport)
    kapt(Dependencies.Dagger.daggerCompiler)
    kapt(Dependencies.Dagger.daggerAndroidProcessor)
}

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}