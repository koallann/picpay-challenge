package com.picpay.desafio.android.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.user.data.UserRepository
import com.picpay.desafio.android.user.domain.User
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserViewModelEvent {
    object ErrorLoadingUsers : UserViewModelEvent()
}

class UserViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    // TODO: emit once
    private val _event: MutableLiveData<UserViewModelEvent> = MutableLiveData()
    val event: LiveData<UserViewModelEvent> = _event

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    fun onLoadUsers() = viewModelScope.launch {
        repository.getUsers().fold(
            { _users.value = it },
            { _event.value = UserViewModelEvent.ErrorLoadingUsers }
        )
    }

}
