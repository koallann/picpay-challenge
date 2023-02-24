package com.picpay.desafio.android.user.data

import com.picpay.desafio.android.user.data.local.UserDAO
import com.picpay.desafio.android.user.data.local.toDomain
import com.picpay.desafio.android.user.data.local.toEntity
import com.picpay.desafio.android.common.network.InternetConnectivity
import com.picpay.desafio.android.user.data.remote.PicPayService
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.data.remote.toDomain
import com.picpay.desafio.android.user.domain.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val internetConnectivity: InternetConnectivity,
    private val service: PicPayService,
    private val userDAO: UserDAO,
) {

    suspend fun getUsers(): Result<List<User>> = try {
        if (internetConnectivity.hasConnection()) {
            getFromRemote()
        } else {
            getFromLocal()
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    private suspend fun getFromRemote(): Result<List<User>> {
        val users = service.getUsers()
        cacheRemoteResult(users)
        return Result.success(users.toDomain())
    }

    private suspend fun getFromLocal(): Result<List<User>> {
        val entities = userDAO.getAll()
        return Result.success(entities.toDomain())
    }

    private suspend fun cacheRemoteResult(users: List<UserResponse>) {
        val entities = users.toEntity()
        userDAO.set(entities)
    }

}
