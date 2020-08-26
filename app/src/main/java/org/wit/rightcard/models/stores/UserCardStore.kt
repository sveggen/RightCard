package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.jetbrains.anko.info
import org.wit.rightcard.models.ShopModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Store

class UserCardStore : Store<UserCardModel> {
    private val firestore = FirebaseFirestore.getInstance()

    override fun getSingle(documentPath: String): UserCardModel {
        var userCardModel = UserCardModel()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document(documentPath)
        GlobalScope.launch(Dispatchers.IO){
            userCardModel = document.get().await().toObject(UserCardModel::class.java)!!
        }
        return userCardModel
    }

    ///////////////////////////////////

    interface MyCallback {
        fun onCallback(list: ArrayList<UserCardModel>)
    }

    //funker - returnerer liste med usercards
    fun readData234(myCallback : MyCallback) {
        val documentdata = firestore.collection("ownedcreditcards")
        documentdata.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<UserCardModel>()
                for (document in task.result!!) {
                    val usercard = document.toObject(UserCardModel::class.java)
                    list.add(usercard)
                }
                myCallback.onCallback(list)
            }
        }
    }

    fun readDataEasy(myCallback: (List<ShopModel>) -> Unit) {
        val documentdata = firestore.collection("shops")
        documentdata.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<ShopModel>()
                for (document in task.result!!) {
                    val shop = document.toObject(ShopModel::class.java)
                    list.add(shop)
                }
                myCallback(list)
            }
        }
    }

    //works
    override fun create(arg: UserCardModel) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("shops").document("1")
        GlobalScope.launch(Dispatchers.IO){
            document.set(arg).await()
        }
    }

    //under construction !!
    override fun update(arg: UserCardModel) {
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.uuid.toString()
        map["name"] = arg.toString()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val document = firestore.collection("ownedcreditcards").document("2")
        GlobalScope.launch(Dispatchers.IO) {
            document.update(map).await()
        }
    }

    //works
    override fun delete(documentPath: String) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        firestore.collection("ownedcreditcards")
            .document(documentPath)
            .delete()

    }

    /**
     * Retrieves all shops in a List of type ShopModel (object).
     */

    override fun search(searchTerm: String): List<UserCardModel> {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<UserCardModel> {
        TODO("Not yet implemented")
    }
}

