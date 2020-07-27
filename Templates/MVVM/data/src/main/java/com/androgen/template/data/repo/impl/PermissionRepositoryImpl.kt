package com.androgen.template.data.repo.impl

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.androgen.template.data.Config
import com.androgen.template.data.model.error.PermissionNotGrantedError
import com.androgen.template.data.repo.PermissionRepository
import io.reactivex.Completable

class PermissionRepositoryImpl : PermissionRepository {

    override fun getPermission(activity: FragmentActivity, permission: String): Completable =
        Completable.create { emitter ->
            activity.supportFragmentManager.beginTransaction()
                .add(PermissionFragment(permission) {
                    if (it) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(PermissionNotGrantedError(permission))
                    }
                }, "Permission").commit()
        }

    private class PermissionFragment(
        private val permission: String,
        private val block: (Boolean) -> Unit
    ) : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = null

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (checkPermission()) {
                block(true)
                detachSelf()
            } else {
                askForPermission()
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == Config.PERMISSIONO_REQ_CODE && grantResults.size == 1) {
                block(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                detachSelf()
            }
        }

        private fun checkPermission(): Boolean =
            ActivityCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED

        private fun askForPermission() {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(permission),
                Config.PERMISSIONO_REQ_CODE
            )
        }

        private fun detachSelf() {
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

    }

}