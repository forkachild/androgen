package com.androgen.template.util

import android.view.View
import androidx.databinding.BindingAdapter

object ForView {

    @JvmStatic
    @BindingAdapter("goneIf")
    fun View.goneIf(condition: Boolean) {
        visibility = if (condition) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun View.goneUnless(condition: Boolean) {
        visibility = if (condition) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("invisibleIf")
    fun View.invisibleIf(condition: Boolean) {
        visibility = if (condition) View.INVISIBLE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("invisibleUnless")
    fun View.invisibleUnless(condition: Boolean) {
        visibility = if (condition) View.VISIBLE else View.INVISIBLE
    }

}