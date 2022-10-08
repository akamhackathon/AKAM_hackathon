package com.ryan.project.mainactivityemployee.repository

import com.ryan.project.entity.Employee
import com.ryan.project.utils.Resource

interface EmployeeMainRepo {

    suspend fun applyForLeave(reason: String, duration: String, date: String): Resource<Any>

    suspend fun getUser(uid: String): Resource<Employee>

}