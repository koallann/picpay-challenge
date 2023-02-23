package com.picpay.desafio.android.user.presentation

import android.util.Log
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

    fun onLoadUsers() = viewModelScope.launch {
        useCase.loadUsers().fold(
            { _users.value = it },
            {
                Log.d(TAG, ":: onLoadUsers :: onFailure - ${it.localizedMessage}")
                _event.value = UserViewModelEvent.ErrorLoadingUsers
            }
        )
    }

    companion object {
        private const val TAG = "UserViewModel"
    }

}
