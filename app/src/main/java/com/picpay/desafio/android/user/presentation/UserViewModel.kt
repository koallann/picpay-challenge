package com.picpay.desafio.android.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.domain.User
import com.picpay.desafio.android.user.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserViewModelEvent {
    object ErrorLoadingUsers : UserViewModelEvent()
}

class UserViewModel @Inject constructor(
    private val useCase: UserUseCase,
) : ViewModel() {

    // TODO: emit once
    private val _event: MutableLiveData<UserViewModelEvent> = MutableLiveData()
    val event: LiveData<UserViewModelEvent> = _event

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    fun onLoadUsers() {
        viewModelScope.launch {
            useCase.loadUsers().fold(
                { _users.postValue(it) },
                { _event.postValue(UserViewModelEvent.ErrorLoadingUsers) }
            )
        }
    }

}
