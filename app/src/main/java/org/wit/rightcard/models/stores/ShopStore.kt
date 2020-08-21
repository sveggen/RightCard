package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Store


class ShopStore : Store<ShopModel>, AnkoLogger {
    private var firestore = FirebaseFirestore.getInstance()


    override fun getSingle(documentPath: String): String {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document(documentPath.toString())
        document.get().addOnSuccessListener {
            val shop = it.toObject(ShopModel::class.java)
            return@addOnSuccessListener
        }
            .addOnFailureListener{ exception ->
                info("get failed with ", exception)
                return@addOnFailureListener
            }
        return "he"
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

    override fun delete(arg: ShopModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("shops").document("1")
        .delete()
            .addOnSuccessListener { info("document deleted") }

    }

    override fun getAll(): List<ShopModel> {
        val list = mutableListOf<ShopModel>()
        firestore.collection("shops")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val shop = document.toObject(ShopModel::class.java)
                    list.add(shop)
                }
            }
            .addOnFailureListener { e ->
                info("Could not receive all documents", e)
            }
        return list
    }
}

