package com.ryan.project.mainactivityemployee.mainfragments.ui.manageleave

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ryan.project.R
import com.ryan.project.databinding.ManageLeaveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageLeaveFragment : Fragment(R.layout.manage_leave) {

    private lateinit var binding: ManageLeaveBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ManageLeaveBinding.bind(view)

        binding.apply {
            applyManage.setOnClickListener {
                findNavController().navigate(
                    ManageLeaveFragmentDirections.actionManageLeaveFragmentToApplyLeaveFragment()
                )
            }
        }

    }
}