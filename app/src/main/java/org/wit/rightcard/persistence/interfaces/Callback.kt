package org.wit.rightcard.persistence.interfaces

interface Callback<T> {
    fun onCallback(list: List<T>)
}