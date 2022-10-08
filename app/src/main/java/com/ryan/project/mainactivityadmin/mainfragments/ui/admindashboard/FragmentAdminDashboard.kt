package com.ryan.project.mainactivityadmin.mainfragments.ui.admindashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.FragmentAdminDashboardBinding

class FragmentAdminDashboard: Fragment(R.layout.fragment_admin_dashboard) {

    private lateinit var binding: FragmentAdminDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAdminDashboardBinding.bind(view)

    }

}