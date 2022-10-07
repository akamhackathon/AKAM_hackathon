package com.ryan.project.api

import com.ryan.project.entity.Employee
import com.ryan.project.entity.Employees
import com.ryan.project.entity.RegisterRf
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class DefaultApi @Inject constructor(
        private val apiService: ApiService,
    ):MainApi{
        override suspend fun getEmployees(): Response<Employees> = apiService.getEmployees()
        override suspend fun registerEmployee(data: RegisterRf): Response<Employee> = apiService.registerUser(data)
    }