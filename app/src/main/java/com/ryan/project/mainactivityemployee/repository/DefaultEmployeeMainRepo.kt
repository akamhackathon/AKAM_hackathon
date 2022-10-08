package com.ryan.project.mainactivityemployee.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.findme.utils.safeCall
import com.ryan.project.entity.Employee
import com.ryan.project.entity.Leave
import com.ryan.project.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class DefaultEmployeeMainRepo : EmployeeMainRepo {

    private val auth = FirebaseAuth.getInstance()
    private val leaves = FirebaseFirestore.getInstance().collection("leaves")
    private val employees = FirebaseFirestore.getInstance().collection("employees")

    override suspend fun applyForLeave(
        reason: String,
        duration: String,
        date: String
    ): Resource<Any> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val uid = UUID.randomUUID().toString()
                val leave =
                    Leave(
                        uid = uid,
                        reason = reason,
                        duration = duration,
                        date = date,
                        employeeId = auth.currentUser!!.uid
                    )
                leaves.document(uid).set(leave).await()
                Resource.Success(Any())
            }
        }
    }

    override suspend fun getUser(uid: String): Resource<Employee> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val employee =
                    employees.document(uid).get().await().toObject(Employee::class.java)!!
                Resource.Success(employee)
            }
        }
    }
}