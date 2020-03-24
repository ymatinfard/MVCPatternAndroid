package com.matinfard.mvcpatternandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StackOverflowApi {
    @GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY.toString() + "&sort=activity&order=desc&site=stackoverflow&filter=withbody")
    fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int?): Call<QuestionsListResponseSchema?>

    @GET("/questions/{questionId}?key=" + Constants.STACKOVERFLOW_API_KEY.toString() + "&site=stackoverflow&filter=withbody")
    fun fetchQuestionDetails(@Path("questionId") questionId: String?): Call<QuestionDetailsResponseSchema?>?
}