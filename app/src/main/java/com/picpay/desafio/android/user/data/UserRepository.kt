package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.user.domain.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: PicPayService,
) {

    suspend fun getUsers(): Result<List<User>> = try {
        val response = service.getUsers()
        when {
            response.isSuccessful -> {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("No result"))
            }
            else -> {
                Result.failure(Exception(response.message()))
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}
