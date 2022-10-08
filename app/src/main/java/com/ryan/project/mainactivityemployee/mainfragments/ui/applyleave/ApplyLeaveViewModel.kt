package com.ryan.project.mainactivityemployee.mainfragments.ui.applyleave

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.project.mainactivityemployee.repository.EmployeeMainRepo
import com.ryan.project.utils.Events
import com.ryan.project.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplyLeaveViewModel @Inject constructor(
    private val repository: EmployeeMainRepo
) : ViewModel() {

    private val _applyLeaveStatus = MutableLiveData<Events<Resource<Any>>>()
    val applyLeaveStatus: LiveData<Events<Resource<Any>>> = _applyLeaveStatus

    fun applyForLeave(reason: String, duration: String, date: String) {
        val error = if(reason.isEmpty()){
            "emptyReason"
        } else if (duration.isEmpty()){
            "emptyDuration"
        } else if (date.isEmpty()){
            "emptyDate"
        } else null

        error?.let {
            _applyLeaveStatus.postValue(Events(Resource.Error(error)))
        }

        _applyLeaveStatus.postValue(Events(Resource.Loading()))

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.applyForLeave(reason, duration, date)
            _applyLeaveStatus.postValue(Events(result))
        }
    }

}