package org.wit.rightcard.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreditCardStore {
    lateinit var database: DatabaseReference

    fun loadDatabase() {
        database = Firebase.database.reference

        val avcreditcards: List<CreditCardModel> = mutableListOf(
            CreditCardModel("Norwegian", "Norwegian")
        )

        avcreditcards.forEach {
            val key = database.child("creditcards").push().key
            it.uuid = key
            if (key != null) {
                database.child("creditcards").child(key).setValue(it)
            }
        }
    }
}