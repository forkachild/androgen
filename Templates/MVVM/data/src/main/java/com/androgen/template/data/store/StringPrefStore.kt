package com.androgen.template.data.store

import android.content.Context

abstract class StringPrefStore(key: String, context: Context, name: String) :
    PrefStore<String>(key, context, name) {

    override fun put(data: String) {
        prefs.edit().putString(key, data).apply()
    }

    override fun get(): String? {
        return prefs.getString(key, null)
    }

    override fun remove() {
        prefs.edit().remove(key).apply()
    }

}