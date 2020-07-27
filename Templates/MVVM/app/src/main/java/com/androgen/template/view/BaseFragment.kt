package com.androgen.template.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.androgen.template.BR

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel>(private val layoutRes: Int) :
    Fragment(), IView {

    private lateinit var _binding: V
    protected val binding: V get() = _binding

    protected abstract val vm: VM

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        _binding.lifecycleOwner = this
        _binding.setVariable(BR.vm, vm)
        return _binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreated(savedInstanceState)
    }

}