package org.wit.rightcard.models.interfaces

import org.wit.rightcard.models.stores.MyCallback


interface Store<T> {

    fun getSingle(documentPath: String): T
    fun getAll(myCallback : MyCallback)
    fun search(searchTerm : String): List<T>
    fun create(arg : T)
    fun update(arg : T)
    fun delete(documentPath: String)
}