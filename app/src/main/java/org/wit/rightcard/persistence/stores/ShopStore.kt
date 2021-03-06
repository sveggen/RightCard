package org.wit.rightcard.persistence.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.persistence.models.ShopModel
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.interfaces.SingleCallback
import org.wit.rightcard.persistence.interfaces.Store

/**
 * Handles all database calls for ShopModel.
 */
class ShopStore : Store<ShopModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("shops")

    /**
     * Retrieves all shops.
     */
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

    /**
     * Retrieves the shop that matches the String provided.
     */
    fun query(shop : String, mySingleCallback: SingleCallback<ShopModel>) {
        firestore.collection("shops")
            .whereIn("name", listOf(shop))
            .get()
            .addOnCompleteListener { task ->
                val shopModel = ShopModel()
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        shopModel.id = document["id"].toString()
                        shopModel.name = shop
                    }
                }
                mySingleCallback.onCallback(shopModel)
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

