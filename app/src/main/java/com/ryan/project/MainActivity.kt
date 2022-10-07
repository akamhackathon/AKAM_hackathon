package com.ryan.project

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ryan.project.utils.Constants.PERMISSION_LOCATION_REQUEST_CODE
import com.ryan.project.utils.Constants.RATIONAL_MSG
import com.ryan.project.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavBar = findViewById(R.id.main_bottom_nav)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFragmen =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container_main) as NavHostFragment
        navController = navHostFragmen.findNavController()

        appBarConfig = AppBarConfiguration.Builder(
            setOf(
                R.id.dashboardFragment,
                R.id.fragmentProfile,
                R.id.fragmentMyAttendance
            )
        ).build()

        setupActionBarWithNavController(navController, appBarConfig)
        bottomNavBar.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)

        requestLocationPermission()

    }

    private fun hasLocationPermission() =
        //comment
        EasyPermissions.hasPermissions(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() =
        EasyPermissions.requestPermissions(
            this,
            RATIONAL_MSG,
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
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
        Toast.makeText(this, "permission Granted", Toast.LENGTH_LONG)
            .show()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestLocationPermission()
        }
    }


}