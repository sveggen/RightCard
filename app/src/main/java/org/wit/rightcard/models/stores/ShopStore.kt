package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class ShopStore : Store<ShopModel>, AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("shops")

    override fun get(myCallback : Callback<ShopModel>) {
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
        documentdata
            .document(arg.id.toString())
            .update(map)
    }

    override fun delete(documentPath: String) {
        documentdata
            .document(documentPath)
            .delete()
    }

    override fun create(arg: ShopModel) {
        arg.id = randomId()
        documentdata
            .document(arg.id.toString())
            .set(arg)
    }

}

