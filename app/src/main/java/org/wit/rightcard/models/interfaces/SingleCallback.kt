package org.wit.rightcard.models.interfaces

interface SingleCallback<T> {
    fun onCallback(arg: T)
}