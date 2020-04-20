package org.wit.rightcard.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserCreditCardStore {

    lateinit var database: DatabaseReference

    fun addCard(useruuid: String?, ccuuid: String?){
        //add card to users collection
            database = Firebase.database.reference

            val avcreditcards: List<UserCreditCardModel> = mutableListOf(
                UserCreditCardModel("uuid", ccuuid, "Credit Card", useruuid)
            )

            avcreditcards.forEach {
                val key = database.child("usercreditcards").push().key
                it.uuid = key
                if (key != null) {
                    database.child("usercreditcards").child(key).setValue(it)
                }
            }
        }

    }

    fun updateCard(nickname: String?){
        //nickname = new nickname.
    }

    fun deleteCard(carduid: String?){
        //delete card with specified uid form db and users collection
    }

