package com.androgen.template.model

class EmptyEvent {

    private var isConsumed = false

    fun consume(): Boolean {
        if (!isConsumed) {
            isConsumed = true
            return true
        }

        return false
    }

}