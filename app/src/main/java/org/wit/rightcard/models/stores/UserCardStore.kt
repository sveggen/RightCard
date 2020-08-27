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
        auth = FirebaseAuth.getInstance()
        val documentdata = firestore.collection("ownedcreditcards").whereIn("userid", listOf(auth.uid))
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
            .document(arg.id.toString())
            .set(arg)
    }

    override fun update(arg: UserCardModel) {
        auth = FirebaseAuth.getInstance()
        val map = mutableMapOf<String, Any>()
        map["uuid"] = arg.id.toString()
        map["creditcardid"] = arg.creditcardid.toString()
        map["creditcardname"] = arg.creditcardname.toString()
        map["nickname"] = arg.nickname.toString()
        map["userid"] = auth.uid.toString()
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

