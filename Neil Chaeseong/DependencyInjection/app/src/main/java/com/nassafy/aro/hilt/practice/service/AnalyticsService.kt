package com.nassafy.aro.hilt.practice.service

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

interface AnalyticsService {
    fun analyticsMethods()
} // End of AnalyticsService

// 인터페이스는 생성자 삽입을 못함. 따라서 구현체 필요
class AnalyticsServiceImpl @Inject constructor(

): AnalyticsService {
    override fun analyticsMethods() {
    }
} // End of AnalyticsServiceImpl

//동일한 유형에 대해 다양한 구현을 제공하는 경우
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

//모듈 내 어노테이션으로 인터페이스 생성자 삽입시 사용할 구현체를 알림
@Module
//ActivityComponent.class 대신 ActivityComponent::class
//모든 종속 항목을 앱의 모든 활동에서 사용할 수 있음.
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {
    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: AnalyticsServiceImpl
    ): AnalyticsService

    //외부 라이브러리의 클래스 주입할 경우 @Provides 사용
    /*@Provides
    fun provideAnalyticsService(

    ): AnalyticsService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(AnalyticsService::class.java)
    }*/

} // End of AnalyticsModule

/*
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(otherInterceptor)
            .build()
    }
}*/
