package com.picpay.desafio.android.domain

import com.picpay.desafio.android.UserFakeData
import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.domain.UserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

class UserUseCaseTest {

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var useCase: UserUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = UserUseCase(repository)
    }

    @Test
    fun `should get the users from repository on use case to load users`(): Unit = runBlocking {
        `when`(repository.getUsers()).thenReturn(Result.success(UserFakeData.domainUsers))

        val result = useCase.loadUsers()

        assert(result.isSuccess)
        verify(repository, only()).getUsers()
    }

}
