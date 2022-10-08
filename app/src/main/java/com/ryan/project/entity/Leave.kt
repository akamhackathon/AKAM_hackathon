package com.ryan.project.entity

data class Leave(
    val uid: String = "",
    val reason: String = "",
    val duration: String = "",
    val date: String = "",
    val employeeId: String = "",
    val status: String = "pending"
)
