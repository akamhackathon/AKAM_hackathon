package com.ryan.project.api

import com.ryan.project.entity.Employees
import retrofit2.Response
import javax.inject.Inject

class DefaultApi {
    class ApiHelperImpl @Inject constructor(
        private val apiService: ApiService
    ):MainApi{
        override suspend fun getEmployees(): Response<Employees> = apiService.getEmployees()
    }
}