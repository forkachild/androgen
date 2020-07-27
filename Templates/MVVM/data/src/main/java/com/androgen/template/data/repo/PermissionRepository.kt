package com.androgen.template.data.repo

import androidx.fragment.app.FragmentActivity
import io.reactivex.Completable

interface PermissionRepository {

    fun getPermission(activity: FragmentActivity, permission: String): Completable

}