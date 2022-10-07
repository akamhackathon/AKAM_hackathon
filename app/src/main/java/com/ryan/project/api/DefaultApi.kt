package com.ryan.project.api

import com.ryan.project.entity.Employee
import com.ryan.project.entity.Employees
import com.ryan.project.entity.LoginResponse
import com.ryan.project.entity.LoginRf
import retrofit2.Response
import javax.inject.Inject

class DefaultApi @Inject constructor(
        private val apiService: ApiService,
    ):MainApi{
        override suspend fun getEmployees(): Response<Employees> = apiService.getEmployees()
        override suspend fun registerEmployee(data: Employee): Response<Employee> = apiService.registerUser(data)
    override suspend fun loginEmployee(data: LoginRf): Response<LoginResponse> = apiService.loginUser(data)
}