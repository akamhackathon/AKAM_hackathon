package com.ryan.project.authentication.authfragments.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ryan.project.R
import com.ryan.project.databinding.FragmentLoginBinding
import com.ryan.project.utils.EventObserver
import com.ryan.project.utils.hideKeyboard
import com.ryan.project.utils.showProgress
import com.ryan.project.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        subscribeToObserve()

        binding = FragmentLoginBinding.bind(view)

        binding.apply {

            btnLogin.setOnClickListener {
                hideKeyboard(requireActivity())
                viewModel.login(etEmail.text?.trim().toString(), etPassword.text?.trim().toString())
            }

        }

    }

    private fun subscribeToObserve() {
        viewModel.loginStatus.observe(viewLifecycleOwner, EventObserver(
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
                    "password" -> {
                        binding.etPassword.error = "Password should be of minimum 6 length"
                    }
                    "invalidPassword" -> {
                        binding.etPassword.error =
                            "Password should contain atleast 1 Capital letter, 1 special character, 1 number and 1 small letter"
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
        ) { result ->
            showProgress(
                activity = requireActivity(),
                bool = false,
                parentLayout = binding.parentLayout,
                cvProgress = binding.cvProgress
            )
            binding.apply {
                etEmail.setText("")
                etPassword.setText("")
            }
            snackbar("Logged in successfully!!")
        })
    }


}