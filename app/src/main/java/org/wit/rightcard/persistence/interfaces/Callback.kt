package org.wit.rightcard.persistence.interfaces

/**
 * Interface for retrieving data from asynchronous operations
 * as a list of objects.
 *
 */
interface Callback<T> {
    fun onCallback(list: List<T>)
}