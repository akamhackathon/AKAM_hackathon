package com.ryan.project.authentication.repository

import com.project.findme.utils.safeCall
import com.ryan.project.api.MainApi
import com.ryan.project.entity.Employee
import com.ryan.project.entity.LoginResponse
import com.ryan.project.entity.LoginRf
import com.ryan.project.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultAuthRepo : AuthRepo {
    override suspend fun register(data: Employee): Resource<Employee> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Resource<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): Resource<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Resource<Boolean> {
        TODO("Not yet implemented")
    }

}