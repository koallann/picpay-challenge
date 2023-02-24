package com.picpay.desafio.android.data

import com.picpay.desafio.android.UserFakeData
import com.picpay.desafio.android.common.network.InternetConnectivity
import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.data.local.UserDAO
import com.picpay.desafio.android.user.data.remote.PicPayService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.never
import org.mockito.kotlin.only
import org.mockito.kotlin.verify

class UserRepositoryTest {

    @Mock
    private lateinit var internetConnectivity: InternetConnectivity

    @Mock
    private lateinit var service: PicPayService

    @Mock
    private lateinit var dao: UserDAO

    private lateinit var repository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = UserRepository(internetConnectivity, service, dao)
    }

    @Test
    fun `should return failure if an unknown error occurs`(): Unit = runBlocking {
        `when`(internetConnectivity.hasConnection()).thenReturn(true)
        `when`(service.getUsers()).doAnswer { throw Exception("Unknown error") }

        val result = repository.getUsers()
        val error = result.exceptionOrNull()

        assert(result.isFailure)
        assert(error != null && error.message == "Unknown error")
    }

    @Test
    fun `should get the users from remote if has internet connection`(): Unit = runBlocking {
        `when`(internetConnectivity.hasConnection()).thenReturn(true)
        `when`(service.getUsers()).thenReturn(UserFakeData.remoteUsers)

        val result = repository.getUsers()

        assert(result.isSuccess)
        assert(result.getOrThrow() == UserFakeData.domainUsers)
        verify(service, only()).getUsers()
        verify(dao, never()).getAll()
    }

    @Test
    fun `should cache the result when get the users from remote`(): Unit = runBlocking {
        `when`(internetConnectivity.hasConnection()).thenReturn(true)
        `when`(service.getUsers()).thenReturn(UserFakeData.remoteUsers)
        `when`(dao.set(anyList())).thenReturn(Unit)

        val result = repository.getUsers()

        assert(result.isSuccess)
        assert(result.getOrThrow() == UserFakeData.domainUsers)
        verify(service, only()).getUsers()
        verify(dao, only()).set(anyList())
    }

    @Test
    fun `should get the users from local if hasn't internet connection`(): Unit = runBlocking {
        `when`(internetConnectivity.hasConnection()).thenReturn(false)
        `when`(dao.getAll()).thenReturn(UserFakeData.localUsers)

        val result = repository.getUsers()

        assert(result.isSuccess)
        assert(result.getOrThrow() == UserFakeData.domainUsers)
        verify(dao, only()).getAll()
        verify(service, never()).getUsers()
    }

}
