package com.ryan.project.mainactivity.mainfragments.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
    }
}