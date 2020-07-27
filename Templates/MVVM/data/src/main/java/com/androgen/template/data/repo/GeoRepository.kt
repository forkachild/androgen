package com.androgen.template.data.repo

import com.androgen.template.data.model.LocationModel
import io.reactivex.Single

interface GeoRepository {

    fun getCurrentLocation(): Single<LocationModel>

}