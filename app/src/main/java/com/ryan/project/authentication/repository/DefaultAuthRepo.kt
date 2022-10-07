package com.ryan.project.authentication.repository

import com.ryan.project.entity.Employee
import com.ryan.project.utils.Resource

class DefaultAuthRepo: AuthRepo {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): Resource<Employee> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Resource<Employee> {
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