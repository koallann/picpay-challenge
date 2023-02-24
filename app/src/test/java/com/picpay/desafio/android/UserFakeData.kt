package com.picpay.desafio.android

import com.picpay.desafio.android.user.data.local.UserEntity
import com.picpay.desafio.android.user.data.remote.UserResponse
import com.picpay.desafio.android.user.domain.User

object UserFakeData {

    val domainUsers: List<User> = listOf(
        User(1, "User 1", "user_1", "https://link.to/user-1"),
        User(2, "User 2", "user_2", "https://link.to/user-2"),
        User(3, "User 3", "user_3", "https://link.to/user-3"),
        User(4, "User 4", "user_4", "https://link.to/user-4"),
        User(5, "User 5", "user_5", "https://link.to/user-5"),
    )

    val remoteUsers: List<UserResponse> = domainUsers.map {
        UserResponse(it.id, it.name, it.username, it.imgUrl)
    }

    val localUsers: List<UserEntity> = domainUsers.map {
        UserEntity(it.id, it.name, it.username, it.imgUrl)
    }

}
