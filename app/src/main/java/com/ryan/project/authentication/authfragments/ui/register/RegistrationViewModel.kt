package com.ryan.project.authentication.authfragments.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.project.authentication.repository.AuthRepo
import com.ryan.project.entity.Employee
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
    private val repository: AuthRepo
) : ViewModel() {

    private val _registerStatus = MutableLiveData<Events<Resource<Employee>>>()
    val registerStatus: LiveData<Events<Resource<Employee>>> = _registerStatus

    fun register(
        employeeId: String,
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
        } else if(employeeId.isEmpty()){
            "emptyId"
        } else if(name.isEmpty()){
            "emptyName"
        } else if(contact.isEmpty()){
            "emptyContact"
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

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.register(
                Employee(
                    name = name,
                    email = email,
                    unique_id = employeeId,
                    phone_no = contact,
                    dept_name = departmentName,
                    photo = "",
                )
            )
            _registerStatus.postValue(Events(result))
        }

    }
}