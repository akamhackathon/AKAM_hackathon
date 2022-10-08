package com.ryan.project.mainactivityemployee.mainfragments.ui.myattendance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.FragmentMyAttendanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMyAttendance: Fragment(R.layout.fragment_my_attendance) {
    private lateinit var binding: FragmentMyAttendanceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyAttendanceBinding.bind(view)
    }
}