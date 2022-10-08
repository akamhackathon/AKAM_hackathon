package com.ryan.project.mainactivityemployee.mainfragments.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.project.entity.Employee
import com.ryan.project.mainactivityemployee.repository.EmployeeMainRepo
import com.ryan.project.utils.Events
import com.ryan.project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: EmployeeMainRepo
): ViewModel() {

    private val _userStatus = MutableLiveData<Events<Resource<Employee>>>()
    val userStatus: LiveData<Events<Resource<Employee>>> = _userStatus

    fun getUser(uid: String){
        _userStatus.postValue(Events(Resource.Loading()))
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getUser(uid)
        }
    }

}