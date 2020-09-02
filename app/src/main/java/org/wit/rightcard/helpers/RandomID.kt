package org.wit.rightcard.helpers

import java.util.*

/**
 * Common function to generate UUID
 */
fun randomId(): String{
    return UUID.randomUUID().toString()
}