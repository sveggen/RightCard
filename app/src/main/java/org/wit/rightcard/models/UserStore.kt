package org.wit.rightcard.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserStore {

    lateinit var database: DatabaseReference

fun createUser(uid: String?, email: String?){
    //Database reference
    database = Firebase.database.reference

    val user: List<UserModel> = mutableListOf(
        UserModel(uid, email)
    )

    user.forEach {
        val key = database.child("users").push().key
        it.uuid = key
        if (key != null) {
            database.child("users").child(key).setValue(it)
        }
    }
}
    fun removeUser() {
}


}