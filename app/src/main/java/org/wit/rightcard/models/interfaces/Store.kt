package org.wit.rightcard.models.interfaces

interface Store<T> {
    fun get(myCallback : Callback<T>)
    fun create(arg : T)
    fun update(arg : T)
    fun delete(documentPath: String)
}