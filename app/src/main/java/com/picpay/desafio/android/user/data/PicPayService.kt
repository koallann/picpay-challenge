package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.user.domain.User
import retrofit2.Call
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    fun getUsers(): Call<List<User>>
}
