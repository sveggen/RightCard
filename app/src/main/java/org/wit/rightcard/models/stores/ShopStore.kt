package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Store


class ShopStore : Store<ShopModel>, AnkoLogger {
    var firestore = FirebaseFirestore.getInstance()


    override fun findAll(): List<ShopModel> {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document("1")
        document.get().addOnSuccessListener { documentSnapshot ->
            val shop = documentSnapshot.toObject<ShopModel>()

        }
            .addOnFailureListener { exception ->
                info("get failed with ", exception)
            }
    }

    override fun create(arg: ShopModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("shops").document("1")
            .set(arg)
            .addOnSuccessListener {info("DocumentSnapshot successfully written!") }
            .addOnFailureListener{e -> info( "Error writing document", e) }
    }

    override fun update(arg: ShopModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("shops").document("1")
        .update("title", "test")
            .addOnSuccessListener { info("updated document") }
    }

    override fun delete(shopModel: ShopModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("shops").document("1")
        .delete()
            .addOnSuccessListener { info("document deleted") }

    }
}

