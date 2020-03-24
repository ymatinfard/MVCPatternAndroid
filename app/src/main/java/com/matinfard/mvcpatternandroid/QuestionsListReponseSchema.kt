package com.matinfard.mvcpatternandroid

import com.google.gson.annotations.SerializedName


data class QuestionsListResponseSchema(@field:SerializedName("items") val questions: List<QuestionSchema>)