package com.ryan.project.api

import com.ryan.project.entity.Employees
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("") // Type endpoint here
    suspend fun getEmployees(): Response<Employees>
}