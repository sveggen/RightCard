package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Store


class ShopStore : Store<ShopModel>, AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()

    override fun getSingle(documentPath: String): ShopModel {
        var shopModel = ShopModel()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document(documentPath)
        GlobalScope.launch(Dispatchers.IO){
            shopModel = document.get().await().toObject(shopModel::class.java)!!

        }
        return shopModel
    }

    //works
    override fun create(arg: ShopModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document("1")
            GlobalScope.launch(Dispatchers.IO){
                document.set(arg).await()
            }
    }

    //under construction !!
    override fun update(arg: ShopModel) {
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.uuid.toString()
        map["name"] = arg.name.toString()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document("2")
        GlobalScope.launch(Dispatchers.IO) {
            document.update(map).await()
        }
    }

    //works
    override fun delete(documentPath: String) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document(documentPath)
        GlobalScope.launch(Dispatchers.IO) {
            document.delete().await()
        }
    }

    /**
     * Retrieves all shops in a List of type ShopModel (object).
     */
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
        info("LIST")
        info(list)
        return list
    }

    override fun search(searchTerm: String): List<ShopModel> {
        TODO("Not yet implemented")
    }
}

