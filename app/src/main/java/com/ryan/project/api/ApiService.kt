package com.ryan.project.api

import android.provider.ContactsContract
import com.ryan.project.entity.Employee
import com.ryan.project.entity.Employees
import com.ryan.project.entity.LoginResponse
import com.ryan.project.entity.LoginRf
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("") // Type endpoint here
    suspend fun getEmployees(): Response<Employees>

    @POST("api/signup")
    suspend fun registerUser(@Body data: Employee): Response<Employee>

    @POST("api/signin")
    suspend fun  loginUser(@Body data: LoginRf): Response<LoginResponse>

    @POST("api/forgot-password")
    suspend fun forgotPass(@Body email: String) : Response<Boolean>

}