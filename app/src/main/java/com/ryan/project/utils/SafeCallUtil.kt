package com.project.findme.utils

import com.ryan.project.utils.Resource

inline fun <T> safeCall(action:() -> Resource<T>): Resource<T> {
    return try{
        action()
    } catch(e: Exception) {
        Resource.Error(e.message ?: "An unknown Error occurred")
    }
}