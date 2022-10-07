package com.ryan.project.authentication.repository

import com.ryan.project.entity.Employee
import com.ryan.project.utils.Resource

interface AuthRepo {
    suspend fun register(data: Employee): Resource<Employee>

    suspend fun login(email: String, password: String): Resource<Employee>

    suspend fun forgotPassword(email: String): Resource<Boolean>

    suspend fun changePassword(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Resource<Boolean>
}