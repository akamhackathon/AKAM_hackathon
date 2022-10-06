package com.ryan.project.api

import com.ryan.project.entity.Employees
import retrofit2.Response

interface MainApi {
    suspend fun getEmployees(): Response<Employees>
}