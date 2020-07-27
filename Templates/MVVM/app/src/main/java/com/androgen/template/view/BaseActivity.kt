package com.androgen.template.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.androgen.template.BR

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel>(private val layoutRes: Int) :
    AppCompatActivity(), IView {

    private lateinit var _binding: V
    protected val binding: V get() = _binding

    protected abstract val vm: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutRes)
        _binding.lifecycleOwner = this
        _binding.setVariable(BR.vm, vm)
        onCreated(savedInstanceState)
    }

}