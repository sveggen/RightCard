package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class ShopStore : Store<ShopModel>, AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()

    override fun getSingle(documentPath: String): ShopModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback : Callback<ShopModel>) {
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

    override fun update(arg: ShopModel) {
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.id.toString()
        map["name"] = arg.name.toString()
        firestore.collection("shops")
            .document("1")
            .update(map)
    }

    override fun delete(documentPath: String) {
        firestore.collection("shops")
            .document(documentPath)
            .delete()
    }

    override fun create(arg: ShopModel) {
        firestore.collection("shops")
            .document("1")
            .set(arg)
    }
}

