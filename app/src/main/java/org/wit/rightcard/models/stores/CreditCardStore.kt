package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.CreditCardModel
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Store

class CreditCardStore : Store<CreditCardModel>, AnkoLogger {

    override fun findAll(): List<CreditCardModel> {
        TODO("Not yet implemented")
    }

    override fun create(arg: CreditCardModel) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("test2").document("5")
            .set(arg)
            .addOnSuccessListener {info("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> info( "Error writing document", e) }
    }

    override fun update(arg: CreditCardModel) {
        TODO("Not yet implemented")
    }

    override fun delete(arg: CreditCardModel) {
        TODO("Not yet implemented")
    }


}