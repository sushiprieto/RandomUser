package com.carlosprieto.randomuser.data

import com.carlosprieto.randomuser.data.dto.User

interface UserHandlerInterface {
    suspend fun getUsers(total: Int): RestResult<List<User>>
}