package com.ryan.project.entity

data class Employee (
    val name: String = "",
    val email: String = "",
    val unique_id: String = "",
    val encrypted_password: String = "",
    val salt: String = "",
    val role: String = "",
    val phone_no: String = "",
    val dept_name: String = "",
    val photo: String = "",
    val profile_image: String = "",
    val isVerified: Boolean = false
)