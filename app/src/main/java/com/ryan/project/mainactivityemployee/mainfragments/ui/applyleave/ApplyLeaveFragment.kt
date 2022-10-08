package com.ryan.project.mainactivityemployee.mainfragments.ui.applyleave

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ryan.project.R
import com.ryan.project.databinding.ApplyLeaveBinding
import com.ryan.project.utils.EventObserver
import com.ryan.project.utils.showProgress
import com.ryan.project.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplyLeaveFragment : Fragment(R.layout.apply_leave) {

    private lateinit var binding: ApplyLeaveBinding
    private lateinit var viewModel: ApplyLeaveViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ApplyLeaveViewModel::class.java]
        subscribeToObserve()

        binding = ApplyLeaveBinding.bind(view)

        binding.apply {
            button.setOnClickListener {
                viewModel.applyForLeave(
                    etReason.text?.trim().toString(),
                    etDuration.text?.trim().toString(),
                    etDate.text?.trim().toString()
                )
            }
        }


    }

    private fun subscribeToObserve() {
        viewModel.applyLeaveStatus.observe(viewLifecycleOwner, EventObserver(
            onError = { error ->
                showProgress(
                    activity = requireActivity(),
                    bool = false,
                    parentLayout = binding.parentLayout,
                    cvProgress = binding.cvProgress
                )
                when (error) {
                    "emptyReason" -> {
                        binding.etReason.error = "Reason cannot be empty"
                    }
                    "emptyDuration" -> {
                        binding.etDuration.error = "Duration cannot be empty"
                    }
                    "emptyDate" -> {
                        binding.etDate.error = "Date cannot be empty"
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
                etReason.setText("")
                etDate.setText("")
                etDuration.setText("")
            }
            findNavController().navigateUp()
            snackbar("Leave application submitted successfully!!")
        })
    }
}
