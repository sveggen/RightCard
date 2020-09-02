package org.wit.rightcard.persistence.interfaces

/**
 * Interface for retrieving data from asynchronous
 * operations as a single object.
 */
interface SingleCallback<T> {
    fun onCallback(arg: T)
}