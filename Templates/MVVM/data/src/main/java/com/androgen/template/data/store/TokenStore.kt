package com.androgen.template.data.store

import android.content.Context
import com.androgen.template.data.Config

class TokenStore(context: Context) : StringPrefStore("token", context, Config.PREF_NAME)