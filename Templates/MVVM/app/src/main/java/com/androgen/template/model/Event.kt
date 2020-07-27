package com.androgen.template.model

class Event<out T>(private val content: T) {

    private var isConsumed = false

    fun consume(): T? {
        if (!isConsumed) {
            isConsumed = true
            return content
        }

        return null
    }

}