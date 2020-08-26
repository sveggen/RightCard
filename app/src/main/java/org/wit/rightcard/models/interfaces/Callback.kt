package org.wit.rightcard.models.interfaces

interface Callback<T> {
    fun onCallback(list: List<T>)
}