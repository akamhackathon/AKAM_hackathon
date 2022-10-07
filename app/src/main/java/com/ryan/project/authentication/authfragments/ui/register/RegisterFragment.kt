package com.ryan.project.authentication.authfragments.ui.register

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.ryan.project.R
import com.ryan.project.databinding.RegistrationBinding
import com.ryan.project.utils.*
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.registration), EasyPermissions.PermissionCallbacks {

    @Inject
    lateinit var glide: RequestManager
    private lateinit var binding: RegistrationBinding
    private lateinit var viewModel: RegistrationViewModel
    private var curImageUri: Uri = Uri.EMPTY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        subscribeToObserve()

        binding = RegistrationBinding.bind(view)

        binding.apply {

            btnRegister.setOnClickListener {
                hideKeyboard(requireActivity())
                viewModel.register(
                    etName.text?.trim().toString(),
                    etEmail.text?.trim().toString(),
                    etPhone.text?.trim().toString(),
                    autoCompleteTvDepartment.text?.trim().toString()
                )
            }

            imageButton.setOnClickListener {
                if(hasCameraPermission()){
                    startCrop()
                }else{
                    requestCameraPermission()
                }
            }

        }

    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            curImageUri = result.uriContent!!
            viewModel.setCurrentImageUri(curImageUri)
        } else {
            val exception = result.error
            snackbar(exception.toString())
        }
    }

    private fun startCrop() {
        cropImage.launch(
            options {
                setGuidelines(CropImageView.Guidelines.ON)
                setAspectRatio(1, 1)
                setCropShape(CropImageView.CropShape.OVAL)
                setOutputCompressQuality(70)
                setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                setImageSource(includeGallery = true, includeCamera = true)
            }
        )
    }

    private fun hasCameraPermission() =
        //comment
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.CAMERA
        )

    private fun requestCameraPermission() =
        EasyPermissions.requestPermissions(
            this,
            Constants.RATIONAL_MSG,
            Constants.PERMISSION_CAMERA_REQUEST_CODE,
            Manifest.permission.CAMERA
        )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        snackbar("permission Granted")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestCameraPermission()
        }
    }

    private fun subscribeToObserve() {
        viewModel.curImageUri.observe(viewLifecycleOwner) {
            binding.textView.isVisible = it == Uri.EMPTY
            if (it == Uri.EMPTY) {
                binding.imageButton.setImageResource(R.drawable.ic_baseline_person_outline_24)
            } else {
                curImageUri = it
                glide.load(curImageUri).into(binding.imageButton)
            }
        }

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