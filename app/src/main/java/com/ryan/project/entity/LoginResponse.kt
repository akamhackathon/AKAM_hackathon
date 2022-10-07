package com.ryan.project.entity

data class LoginResponse(
    val token: String = "",
    val data: Employee? = null
)
