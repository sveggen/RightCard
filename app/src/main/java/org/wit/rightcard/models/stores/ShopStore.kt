package org.wit.rightcard.models.stores

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Store

interface MyCallback {
    fun onCallback(list: ArrayList<ShopModel>)
}

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


    //funker - returnerer liste med shopmodel
    override fun getAll(myCallback : MyCallback) {
        val documentdata = firestore.collection("shops")
        documentdata.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<ShopModel>()
                for (document in task.result!!) {
                    val shop = document.toObject(ShopModel::class.java)
                    list.add(shop)
                }
                myCallback.onCallback(list)
            }
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


    override fun search(searchTerm: String): List<ShopModel> {
        TODO("Not yet implemented")
    }

    override fun create(arg: ShopModel) {
        TODO("Not yet implemented")
    }
}

