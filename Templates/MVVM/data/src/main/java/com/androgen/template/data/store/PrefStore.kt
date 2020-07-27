package com.androgen.template.data.store

import android.content.Context

abstract class PrefStore<T>(protected val key: String, context: Context, name: String) : Store<T> {

    protected val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)

}