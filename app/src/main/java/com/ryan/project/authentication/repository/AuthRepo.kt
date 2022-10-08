package com.ryan.project.authentication.repository

import android.graphics.Bitmap
import com.google.firebase.auth.AuthResult
import com.ryan.project.entity.Employee
import com.ryan.project.utils.Resource

interface AuthRepo {
    suspend fun register(
        name: String,
        email: String,
        contact: String,
        departmentName: String,
        bitmap: Bitmap
    ): Resource<AuthResult>

    suspend fun login(email: String, password: String): Resource<Employee>

    suspend fun forgotPassword(email: String): Resource<Boolean>

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Resource<Any>

    suspend fun getUser(uid: String): Resource<Employee>
}