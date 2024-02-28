package com.carlosprieto.randomuser.data

import com.carlosprieto.randomuser.data.dto.User

class UserHandler(private val homeDataSource: HomeDataSource) : UserHandlerInterface {

    override suspend fun getUsers(total: Int): RestResult<List<User>> {
        return homeDataSource.getUsers(total)
    }
}