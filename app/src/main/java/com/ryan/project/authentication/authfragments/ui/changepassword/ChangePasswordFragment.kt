package com.ryan.project.authentication.authfragments.ui.changepassword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ryan.project.MainActivityEmployee
import com.ryan.project.R
import com.ryan.project.databinding.FragmentChangePasswordBinding
import com.ryan.project.utils.EventObserver
import com.ryan.project.utils.hideKeyboard
import com.ryan.project.utils.showProgress
import com.ryan.project.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {

    private lateinit var viewModel: ChangePasswordViewModel
    private lateinit var binding: FragmentChangePasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ChangePasswordViewModel::class.java)
        subscribeToObserve()

        binding = FragmentChangePasswordBinding.bind(view)

        binding.apply {
            btnChangePassword.setOnClickListener {
                hideKeyboard(activity as Activity)
                viewModel.changePassword(
                    etEditUserPassword.text?.trim().toString(),
                    etEditUserNewPassword.text?.trim().toString()
                )
            }
        }

    }

    private fun subscribeToObserve() {

        viewModel.changePasswordStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                showProgress(
                    activity = requireActivity(),
                    bool = false,
                    parentLayout = binding.parentLayout,
                    cvProgress = binding.cvProgress
                )
                snackbar(it)
            },
            onLoading = {
                showProgress(
                    activity = requireActivity(),
                    bool = true,
                    parentLayout = binding.parentLayout,
                    cvProgress = binding.cvProgress
                )
            }
        ) {
            showProgress(
                activity = requireActivity(),
                bool = false,
                parentLayout = binding.parentLayout,
                cvProgress = binding.cvProgress
            )
            Intent(requireContext(), MainActivityEmployee::class.java).also { intent ->
                startActivity(intent)
                requireActivity().finish()
            }
            snackbar("Password changed successfully!")
        })
    }

}