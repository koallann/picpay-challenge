package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.UserFakeData
import com.picpay.desafio.android.user.domain.User
import com.picpay.desafio.android.user.domain.UserUseCase
import com.picpay.desafio.android.user.presentation.UserViewModel
import com.picpay.desafio.android.user.presentation.UserViewModelEvent
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class UserViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UserUseCase

    @Mock
    private lateinit var usersObserver: Observer<List<User>>

    @Mock
    private lateinit var eventObserver: Observer<UserViewModelEvent>

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserViewModel(useCase)
        viewModel.users.observeForever(usersObserver)
        viewModel.event.observeForever(eventObserver)
    }

    @Test
    fun `should post a users list when the result is success`(): Unit = runBlocking {
        `when`(useCase.loadUsers()).thenReturn(Result.success(UserFakeData.domainUsers))

        viewModel.onLoadUsers()

        verify(usersObserver).onChanged(UserFakeData.domainUsers)
        verify(eventObserver, never()).onChanged(any())
    }

    @Test
    fun `should emit a error event when the result is failure`(): Unit = runBlocking {
        `when`(useCase.loadUsers()).thenReturn(Result.failure(Exception("Unknown error")))

        viewModel.onLoadUsers()

        verify(eventObserver).onChanged(UserViewModelEvent.ErrorLoadingUsers)
        verify(usersObserver, never()).onChanged(anyList())
    }

}
