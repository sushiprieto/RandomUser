package com.carlosprieto.randomuser.data

import com.carlosprieto.randomuser.data.dto.User

class HomeDataSource(private val apiService: ApiService) {

    suspend fun getUsers(total: Int): RestResult<List<User>> {
        return try {
            val response = apiService.getUsers(total)
            if (response.isSuccessful) {
                val users = response.body()?.results
                if (users != null) {
                    RestResult.Success(users)
                } else {
                    RestResult.Error(Exception("Null response"))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Error de red"
                RestResult.Error(Exception("Failed response: $errorBody"))
            }
        } catch (e: Exception) {
            RestResult.Error(e)
        }
    }
}
