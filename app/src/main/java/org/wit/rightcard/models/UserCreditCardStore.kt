package org.wit.rightcard.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

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

private fun saveCardToDB(creditcarduuid: String?){
    val uid = FirebaseAuth.getInstance().uid
    val ref = FirebaseDatabase.getInstance().getReference("/usercreditcards/$uid")

    val userCreditCard = UserCreditCardModel("uuid", creditcarduuid, " ", uid)

    ref.setValue(userCreditCard)
        .addOnSuccessListener {  }
}