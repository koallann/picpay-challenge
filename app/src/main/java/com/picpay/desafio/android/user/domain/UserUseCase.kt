package com.picpay.desafio.android.user.domain

import com.picpay.desafio.android.user.data.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository,
) {

    suspend fun loadUsers(): Result<List<User>> {
        return repository.getUsers() // apply business rules here
    }

}
