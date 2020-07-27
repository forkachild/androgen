package com.androgen.template.data.repo.impl

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import com.androgen.template.data.model.LocationModel
import com.androgen.template.data.repo.GeoRepository
import io.reactivex.Single

class GeoRepositoryImpl(private val locationManager: LocationManager) : GeoRepository {

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Single<LocationModel> =
        Single.create { emitter ->

            locationManager.requestSingleUpdate(
                LocationManager.GPS_PROVIDER,
                object : LocationListener {

                    override fun onLocationChanged(location: Location) {
                        emitter.onSuccess(LocationModel(location.latitude, location.longitude))
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String?) {
                    }

                    override fun onProviderDisabled(provider: String?) {
                    }

                },
                Looper.getMainLooper()
            )
        }

}