package org.wit.rightcard.models.interfaces

import org.wit.rightcard.models.UserModel
import java.util.*

interface Store<T> {
    fun findAll(): List<T>
    fun create(arg : T)
    fun update(arg : T)
    fun delete(arg : T)
}