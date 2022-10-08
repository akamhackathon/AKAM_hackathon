package com.ryan.project.mainactivityemployee.mainfragments.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ryan.project.R
import com.ryan.project.databinding.FragmentDashboardBinding
import com.ryan.project.service.LocationService
import com.ryan.project.utils.EventObserver

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: DashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[DashboardViewModel::class.java]
        subscribeToObserve()

        viewModel.getUser(Firebase.auth.currentUser!!.uid)

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
                findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToManageLeaveFragment()
                )
            }
            fieldWorkDashboard.setOnClickListener {

            }
            overtimeDashboard.setOnClickListener {

            }
        }
    }

    private fun subscribeToObserve() {
        viewModel.userStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {

            },
            onLoading = {

            }, {

            }
        ))
    }
}