package com.ryan.project.mainactivity.mainfragments.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.FragmentDashboardBinding
import com.ryan.project.service.LocationService

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        binding.apply {
            markEntryDashoard.setOnClickListener {
                Intent(requireContext(), LocationService::class.java).also {
                    requireContext().startService(it)
                }
            }
            markExitDashboard.setOnClickListener {
                Intent(requireContext(), LocationService::class.java).also {
                    requireContext().stopService(it)
                }
            }
            manageLeaveDashboard.setOnClickListener {

            }
            fieldWorkDashboard.setOnClickListener {

            }
        }
    }
}