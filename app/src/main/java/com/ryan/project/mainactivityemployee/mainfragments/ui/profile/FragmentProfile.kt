package com.ryan.project.mainactivityemployee.mainfragments.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ryan.project.R
import com.ryan.project.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProfile: Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
    }
}