package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store
import kotlin.collections.ArrayList


class CardStore : Store<CardModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private val documentdata = firestore.collection("creditcards")

    override fun getSingle(documentPath: String): CardModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<CardModel>) {
        documentdata.orderBy("name").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<CardModel>()
                for (document in task.result!!) {
                    val card = document.toObject(CardModel::class.java)
                    list.add(card)
                }
                myCallback.onCallback(list)
            }
        }
    }

    override fun create(arg: CardModel) {
        arg.id = randomId()
        documentdata
            .document(arg.id.toString())
            .set(arg)
    }

    override fun update(arg: CardModel) {
        auth = FirebaseAuth.getInstance()
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.id.toString()
        map["name"] = arg.name.toString()
        map["provider"] = arg.provider.toString()
        map["image"] = arg.image.toString()
        documentdata
            .document("1")
            .update(map)
    }

    override fun delete(documentPath: String) {
        documentdata
            .document(documentPath)
            .delete()
    }

    fun getAllNewCards() {
        val mutablelist = mutableListOf<Any>()


    }
}