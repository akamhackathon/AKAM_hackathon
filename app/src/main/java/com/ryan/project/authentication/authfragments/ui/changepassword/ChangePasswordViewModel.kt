package com.ryan.project.authentication.authfragments.ui.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.project.authentication.repository.AuthRepo
import com.ryan.project.utils.Events
import com.ryan.project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val repository: AuthRepo
) : ViewModel() {

    private val _changePasswordStatus = MutableLiveData<Events<Resource<Any>>>()
    val changePasswordStatus: LiveData<Events<Resource<Any>>> = _changePasswordStatus

    fun changePassword(oldPassword: String, newPassword: String) {

        val pattern: Pattern

        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(newPassword)

        val error = if (oldPassword.isEmpty()) {
            "emptyOld"
        } else if (newPassword.isEmpty()) {
            "emptyNew"
        } else if (newPassword.length < 6) {
            "password"
        } else if (matcher.matches()) {
            "invalidPassword"
        } else null

        error?.let {
            _changePasswordStatus.postValue(Events(Resource.Error(error)))
            return
        }

        _changePasswordStatus.postValue(Events(Resource.Loading()))

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.changePassword(oldPassword, newPassword)
            _changePasswordStatus.postValue(Events(result))
        }
    }

}