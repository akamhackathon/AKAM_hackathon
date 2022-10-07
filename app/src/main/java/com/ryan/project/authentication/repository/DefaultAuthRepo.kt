package com.ryan.project.authentication.repository

import com.project.findme.utils.safeCall
import com.ryan.project.api.MainApi
import com.ryan.project.entity.Employee
import com.ryan.project.entity.RegisterRf
import com.ryan.project.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultAuthRepo
@Inject
constructor(
    private val mainApi: MainApi
) : AuthRepo {
    override suspend fun register(
        email: String,
        password: String
    ): Resource<Employee> = withContext(Dispatchers.IO) {
        safeCall {
            val data = RegisterRf(email, password)
            val result = mainApi.registerEmployee(data)
            Resource.Success(result.body()!!)
        }
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