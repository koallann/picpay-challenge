package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.user.domain.User
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
