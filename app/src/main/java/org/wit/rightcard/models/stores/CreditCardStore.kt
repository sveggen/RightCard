package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.interfaces.StoreInterface

class CreditCardStore : StoreInterface, AnkoLogger {

    override fun findAll(): List<Any> {
        TODO("Not yet implemented")
    }

    override fun create(any: Any) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("test2").document("5")
            .set(any)
            .addOnSuccessListener {info("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> info( "Error writing document", e) }
    }

    override fun update(any: Any) {
        TODO("Not yet implemented")
    }

    override fun delete(any: Any) {
        TODO("Not yet implemented")
    }


}