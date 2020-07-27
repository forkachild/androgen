package com.androgen.template.ui

import android.os.Bundle
import com.androgen.template.R
import com.androgen.template.databinding.LayoutContainerBinding
import com.androgen.template.util.observeEmptyEvent
import com.androgen.template.view.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContainerActivity :
    BaseActivity<LayoutContainerBinding, ContainerViewModel>(R.layout.layout_container) {

    override val vm: ContainerViewModel by viewModel()

    override fun onCreated(savedInstanceState: Bundle?) {
        super.onCreated(savedInstanceState)

        vm.onNavLogin.observeEmptyEvent(this) {

        }

        vm.onNavHome.observeEmptyEvent(this) {

        }
    }

}