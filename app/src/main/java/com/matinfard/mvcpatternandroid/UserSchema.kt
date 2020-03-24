package com.matinfard.mvcpatternandroid

import com.google.gson.annotations.SerializedName

data class UserSchema(
    @SerializedName("display_name")
    val mUserDisplayName: String,
    @SerializedName("profile_image")
    val mUserAvatarUrl: String
)
