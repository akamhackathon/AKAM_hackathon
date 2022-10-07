package com.ryan.project.authentication.authfragments.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ryan.project.R
import com.ryan.project.databinding.RegistrationBinding
import com.ryan.project.utils.EventObserver
import com.ryan.project.utils.hideKeyboard
import com.ryan.project.utils.showProgress
import com.ryan.project.utils.snackbar

class RegisterFragment : Fragment(R.layout.registration) {

    private lateinit var binding: RegistrationBinding
    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        subscribeToObserve()

        binding = RegistrationBinding.bind(view)

        binding.apply {

            btnRegister.setOnClickListener {
                hideKeyboard(requireActivity())
                viewModel.register(
                    etEmployeeId.text?.trim().toString(),
                    etName.text?.trim().toString(),
                    etEmail.text?.trim().toString(),
                    etPhone.text?.trim().toString(),
                    autoCompleteTvDepartment.text?.trim().toString()
                )
            }

        }

    }

    private fun subscribeToObserve() {
        viewModel.registerStatus.observe(viewLifecycleOwner, EventObserver(
            onError = { error ->
                showProgress(
                    activity = requireActivity(),
                    bool = false,
                    parentLayout = binding.parentLayout,
                    cvProgress = binding.cvProgress
                )
                when (error) {
                    "emptyEmail" -> {
                        binding.etEmail.error = "Email cannot be empty"
                    }
                    "email" -> {
                        binding.etEmail.error = "Enter a valid email"
                    }
                    "emptyId" -> {
                        binding.etEmployeeId.error = "Employee Id cannot be empty"
                    }
                    "emptyName" -> {
                        binding.etName.error = "Employee name cannot be empty"
                    }
                    "emptyContact" -> {
                        binding.etPhone.error = "Employee contact number cannot be empty"
                    }
                    "emptyDepartment" -> {
                        snackbar("Please select department name")
                    }
                    else -> snackbar(error)
                }
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
            binding.apply {
                etEmail.setText("")
                etName.setText("")
                etPhone.setText("")
                etEmployeeId.setText("")
            }
            snackbar("Registered successfully!!")
        })
    }

}