package org.wit.rightcard.models.interfaces


interface Store<T> {

    fun getSingle(documentPath: String): T?
    fun getAll(): List<T>
    fun create(arg : T)
    fun update(arg : T)
    fun delete(arg : T)
}