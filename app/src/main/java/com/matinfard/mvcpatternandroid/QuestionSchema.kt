package com.matinfard.mvcpatternandroid

import com.google.gson.annotations.SerializedName


class QuestionSchema(
    @field:SerializedName("title") val mTitle: String,
    @field:SerializedName("question_id") val mId: String,
    @field:SerializedName("body") val mBody: String,
    @field:SerializedName("owner") val mOwner: UserSchema
)