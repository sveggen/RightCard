package org.wit.rightcard.persistence.interfaces

/**
 * Interface for Store-classes to ensure that a standard
 * CRUD-flow is maintained.
 *
 */
interface Store<T> {
    fun get(myCallback : Callback<T>)
    fun create(arg : T)
    fun update(arg : T)
    fun delete(documentPath: String)
}