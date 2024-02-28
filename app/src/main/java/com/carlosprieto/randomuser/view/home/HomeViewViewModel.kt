package com.carlosprieto.randomuser.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosprieto.randomuser.data.RestResult
import com.carlosprieto.randomuser.data.UserHandlerInterface
import com.carlosprieto.randomuser.data.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewViewModel : ViewModel(), KoinComponent {

    private val userHandler: UserHandlerInterface by inject()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    init {
        getUsers(20)
    }

    fun getUsers(total: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userHandler.getUsers(total)
                when (result) {
                    is RestResult.Success -> {
                        _users.value = result.data
                    }
                    is RestResult.Error -> {
                        Log.e("TAG", "getUsers: ", )
                    }
                }
            } catch (e: Exception) {
                Log.e("TAG", "getUsers: ", )
            }
        }
    }
}