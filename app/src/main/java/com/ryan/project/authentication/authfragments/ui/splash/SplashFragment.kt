package com.ryan.project.authentication.authfragments.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ryan.project.MainActivityAdmin
import com.ryan.project.MainActivityEmployee
import com.ryan.project.R
import com.ryan.project.databinding.SplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_screen) {

    private lateinit var binding: SplashScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Firebase.auth.currentUser?.uid != null){
            Intent(requireContext(), MainActivityAdmin::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        }

        binding = SplashScreenBinding.bind(view)

        binding.apply {
            getStarted.setOnClickListener {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
            }
        }

    }

}