package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store


class UserCardStore : Store<UserCardModel>, AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private val documentdata = firestore.collection("ownedcreditcards")

    override fun getSingle(documentPath: String): UserCardModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<UserCardModel>) {
        auth = FirebaseAuth.getInstance()
        documentdata.whereIn("userid", listOf(auth.uid))
           .get().addOnCompleteListener { task ->
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
        documentdata
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
        documentdata
            .document(arg.id.toString())
            .update(map)
    }

    override fun delete(documentPath: String) {
        documentdata
            .document(documentPath)
            .delete()
    }

    fun updateNickname(arg: UserCardModel){
        auth = FirebaseAuth.getInstance()
        documentdata
            .document(auth.uid + arg.creditcardid)
            .update("nickname", arg.nickname)
    }
}

