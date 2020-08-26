package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store


class UserCardStore : Store<UserCardModel> {
    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun getSingle(documentPath: String): UserCardModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<UserCardModel>) {
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

    override fun create(arg: UserCardModel) {
        firestore.collection("ownedcreditcards")
            .document("1")
            .set(arg)
    }

    override fun update(arg: UserCardModel) {
        auth = FirebaseAuth.getInstance()
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.uuid.toString()
        map["creditcarduuid"] = arg.creditcarduuid.toString()
        map["creditcardname"] = arg.creditcardname.toString()
        map["nickname"] = arg.nickname.toString()
        map["useruuid"] = auth.uid.toString()
        firestore.collection("ownedcreditcards")
            .document("1")
            .update(map)
    }

    override fun delete(documentPath: String) {
        firestore.collection("ownedcreditcards")
            .document(documentPath)
            .delete()
    }
}

