package com.ryan.project.service

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.ryan.project.utils.Constants
import com.ryan.project.utils.Constants.CENTER_LATITUDE
import com.ryan.project.utils.Constants.CENTER_LONGITUDE
import com.ryan.project.utils.Constants.LOCATION_SERVICE_TAG
import javax.inject.Inject

class LocationService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "You are not in campus", Toast.LENGTH_SHORT).show()
        } else {
            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val accuracy = location.accuracy


                    val results = FloatArray(1)


                    Location.distanceBetween(
                        CENTER_LATITUDE, CENTER_LONGITUDE, latitude, longitude, results
                    )
                    val distanceInMeters = results[0]
                    val inCampus = distanceInMeters < Constants.ALLOWED_RADIUS

                    if (inCampus) {
                        Toast.makeText(
                            this@LocationService,
                            "Your entry is marked!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LocationService,
                            "You are not in campus",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            try {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener
                )
            } catch (e: Exception) {
                Log.d("TAG_ERROR", "getLocation: ${e.message}")
            }
        }
        return START_STICKY
    }
}