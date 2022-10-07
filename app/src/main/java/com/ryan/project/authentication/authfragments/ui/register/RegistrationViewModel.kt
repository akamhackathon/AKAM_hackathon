package com.ryan.project.authentication.authfragments.ui.register

import android.content.Context
import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.ryan.project.authentication.repository.AuthRepo
import com.ryan.project.entity.Employee
import com.ryan.project.utils.BitmapUtils
import com.ryan.project.utils.Events
import com.ryan.project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val context: Context,
    private val repository: AuthRepo
) : ViewModel() {

    private val _registerStatus = MutableLiveData<Events<Resource<AuthResult>>>()
    val registerStatus: LiveData<Events<Resource<AuthResult>>> = _registerStatus

    private val _curImageUri = MutableLiveData<Uri>()
    val curImageUri: LiveData<Uri> = _curImageUri

    fun setCurrentImageUri(uri: Uri) {
        _curImageUri.postValue(uri)
    }

    fun register(
        name: String,
        email: String,
        contact: String,
        departmentName: String
    ) {

//        val pattern: Pattern
//
//        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
//
//        pattern = Pattern.compile(PASSWORD_PATTERN)
//        val matcher: Matcher = pattern.matcher(password)

        val error = if (email.isEmpty()) {
            "emptyEmail"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "email"
        } else if (name.isEmpty()) {
            "emptyName"
        } else if (contact.isEmpty()) {
            "emptyContact"
        } else if (curImageUri.value == Uri.EMPTY || curImageUri.value == null) {
            "uri"
        }
//        else if(departmentName.isEmpty()){
//            "emptyDepartment"
//        }
//        else if (password.length != 6) {
//            "password"
//        } else if(matcher.matches()){
//            "invalidPassword"
//        }
        else null

        error?.let {
            _registerStatus.postValue(Events(Resource.Error(error)))
            return
        }

        _registerStatus.postValue(Events(Resource.Loading()))

        val bitmap = BitmapUtils.getBitmapFromUri(context.contentResolver, curImageUri.value!!)

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.register(
                name = name,
                email = email,
                contact = contact,
                departmentName = departmentName,
                bitmap = bitmap,
            )
            _registerStatus.postValue(Events(result))
        }

    }
}