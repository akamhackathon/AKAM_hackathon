package com.ryan.project.entity

data class Employee (
    val name: String = "",
    val email: String = "",
    val unique_id: String = "",
    val role: String = "employee",
    val phone_no: String = "",
    val photo: String = "",
    val isVerified: Boolean = false
)