package com.picpay.desafio.android.user.data.remote

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.user.domain.User

class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("img") val imgUrl: String,
) {
    fun toDomain() = User(id, name, username, imgUrl)
}

fun List<UserResponse>.toDomain(): List<User> = this.map { it.toDomain() }
