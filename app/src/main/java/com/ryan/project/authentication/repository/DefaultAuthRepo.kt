package com.ryan.project.authentication.repository

import android.graphics.Bitmap
import android.util.Base64
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.project.findme.utils.safeCall
import com.ryan.project.entity.Employee
import com.ryan.project.utils.Constants
import com.ryan.project.utils.Constants.DEFAULT_PASSWORD
import com.ryan.project.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class DefaultAuthRepo : AuthRepo {

    val auth = FirebaseAuth.getInstance()
    val employees = FirebaseFirestore.getInstance().collection("employees")

    override suspend fun register(
        name: String,
        email: String,
        contact: String,
        departmentName: String,
        bitmap: Bitmap
    ): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val uid = UUID.randomUUID().toString()
                val baos = ByteArrayOutputStream()

                val resizedBitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    Constants.DST_WIDTH,
                    Constants.DST_HEIGHT, true
                )

                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                val byteArray: String =
                    Base64.encodeToString(
                        baos.toByteArray(),
                        Base64.DEFAULT
                    )

                val employee = Employee(
                    name = name,
                    email = email,
                    unique_id = uid,
                    role = "employee",
                    phone_no = contact,
                    photo = byteArray,
                    isVerified = false,
                    department_id = departmentName
                )

                val result = auth.createUserWithEmailAndPassword(email, DEFAULT_PASSWORD).await()

                employees.document(uid).set(employee).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }

    override suspend fun forgotPassword(email: String): Resource<Boolean> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = auth.sendPasswordResetEmail(email).await()
                Resource.Success(result)
                Resource.Success(true)
            }
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Resource<Any> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val user = Firebase.auth.currentUser

                val credential = EmailAuthProvider
                    .getCredential(user?.email!!, oldPassword)

                val result = user.reauthenticate(credential).addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        user.updatePassword(newPassword).await()
                    }
                }

                Resource.Success(result)
            }
        }
    }

    override suspend fun getUser(uid: String): Resource<Employee> = withContext(Dispatchers.IO) {
        safeCall {
            val employee =
                employees.document(uid).get().await().toObject(Employee::class.java) ?: Employee()
            Resource.Success(employee)
        }
    }

}