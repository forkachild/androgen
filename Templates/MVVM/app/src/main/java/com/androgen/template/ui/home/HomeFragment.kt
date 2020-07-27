package com.androgen.template.ui.home

import com.androgen.template.R
import com.androgen.template.databinding.LayoutHomeBinding
import com.androgen.template.view.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<LayoutHomeBinding, HomeViewModel>(R.layout.layout_home) {

    override val vm: HomeViewModel by viewModel()

}