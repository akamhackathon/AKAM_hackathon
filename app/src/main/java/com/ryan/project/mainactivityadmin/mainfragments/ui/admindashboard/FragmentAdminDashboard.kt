package com.ryan.project.mainactivityadmin.mainfragments.ui.admindashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ryan.project.R
import com.ryan.project.databinding.FragmentAdminDashboardBinding

class FragmentAdminDashboard: Fragment(R.layout.fragment_admin_dashboard) {

    private lateinit var binding: FragmentAdminDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAdminDashboardBinding.bind(view)
        binding.apply {
            addEpTv.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }

            requestSheetTv.setOnClickListener {
                // TODO: API CALL
            }

            leaveReqTv.setOnClickListener {
                findNavController().navigate(R.id.applyLeaveFragment)
            }

            onFieldAttendanceTv.setOnClickListener {
                // TODO: On Field attedndance
            }

            overtimeDetailTv.setOnClickListener {
                // TODO: On Overtime Detail
            }
            employeeListTv.setOnClickListener {
                // TODO: On employee List
            }

        }

    }

}