package com.ryan.project.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ryan.project.utils.Constants.LOCATION_SERVICE_TAG

class LocationService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("ExtraData")
        dataString?.let {
            Log.d(LOCATION_SERVICE_TAG, dataString)
        }
        return START_STICKY
    }
}