package com.androgen.template.data.store

interface Store<T> {

    fun put(data: T)

    fun get(): T?

    fun remove()

}