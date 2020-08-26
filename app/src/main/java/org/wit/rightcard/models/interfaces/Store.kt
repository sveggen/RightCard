package org.wit.rightcard.models.interfaces

interface Store<T> {

    fun getSingle(documentPath: String): T
    fun getAll(myCallback : Callback<T>)
    fun create(arg : T)
    fun update(arg : T)
    fun delete(documentPath: String)
}