package org.wit.rightcard.models.interfaces

import org.wit.rightcard.models.UserModel
import java.util.*

interface StoreInterface {
    fun findAll(): List<Any>
    fun create(any : Any)
    fun update(any: Any)
    fun delete(any: Any)
}