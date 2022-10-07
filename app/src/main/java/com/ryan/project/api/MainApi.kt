package com.ryan.project.api

import com.ryan.project.entity.Employee
import com.ryan.project.entity.Employees
import com.ryan.project.entity.RegisterRf
import retrofit2.Call
import retrofit2.Response

interface MainApi {
    suspend fun getEmployees(): Response<Employees>

    suspend fun registerEmployee(data: RegisterRf): Response<Employee>
}