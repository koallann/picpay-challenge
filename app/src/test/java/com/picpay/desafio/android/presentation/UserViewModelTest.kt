package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.UserFakeData
import com.picpay.desafio.android.user.domain.UserUseCase
import com.picpay.desafio.android.user.presentation.UserViewModel
import com.picpay.desafio.android.user.presentation.UserViewModelEvent
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UserViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UserUseCase

    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(useCase)
    }

    @Test
    fun `should post a users list when the result is success`(): Unit = runBlocking {
        `when`(useCase.loadUsers()).thenReturn(Result.success(UserFakeData.domainUsers))

        viewModel.onLoadUsers()

        assert(viewModel.users.value == UserFakeData.domainUsers)
        assert(viewModel.event.value !is UserViewModelEvent.ErrorLoadingUsers)
    }

    @Test
    fun `should emit a error event when the result is failure`(): Unit = runBlocking {
        `when`(useCase.loadUsers()).thenReturn(Result.failure(Exception("Unknown error")))

        viewModel.onLoadUsers()

        assert(viewModel.event.value is UserViewModelEvent.ErrorLoadingUsers)
        assert(viewModel.users.value == null)
    }

}
