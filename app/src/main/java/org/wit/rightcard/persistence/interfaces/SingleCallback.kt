package org.wit.rightcard.persistence.interfaces

interface SingleCallback<T> {
    fun onCallback(arg: T)
}