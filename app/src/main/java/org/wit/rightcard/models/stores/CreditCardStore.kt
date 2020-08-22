package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.CreditCardModel
import org.wit.rightcard.models.interfaces.Store

class CreditCardStore : Store<CreditCardModel>, AnkoLogger {



    override fun create(arg: CreditCardModel) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("shops").document("5")
            .set(arg)
            .addOnSuccessListener {info("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> info( "Error writing document", e) }
    }

    override fun update(arg: CreditCardModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }

    override fun getSingle(documentPath: String): CreditCardModel {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<CreditCardModel> {
        TODO("Not yet implemented")
    }

    override fun search(searchTerm: String): List<CreditCardModel> {
        TODO("Not yet implemented")
    }


}