package com.ryan.project.mainactivity.mainfragments.ui.manageleave

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.ManageLeaveBinding

class ManageLeaveFragment: Fragment(R.layout.fragment_manage_leave) {

    private lateinit var binding: ManageLeaveBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ManageLeaveBinding.bind(view)

    }
}