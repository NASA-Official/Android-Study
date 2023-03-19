package com.nassafy.aro.hilt.practice.adapter

import com.nassafy.aro.hilt.practice.service.AnalyticsService
import javax.inject.Inject

//Adapter를 주입하기 위해선 매개변수 AnalyticsService도 주입 될 수 있도록 구성해야함.
class AnalyticsAdapter @Inject constructor(
    private val service: AnalyticsService

) {

} // End of AnalyticsAdapter