package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.*
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class CardBenefitsStore : Store<CardBenefitsModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("cardbenefits")

    override fun get(myCallback: Callback<CardBenefitsModel>) {
        TODO("Not yet implemented")
    }

    fun query(usercardids : ArrayList<String>, myCallback: Callback<CardBenefitsModel>) {
        if (usercardids.isNotEmpty()) {
            //get all the cards benefits
            val list = ArrayList<CardBenefitsModel>()
            documentdata
                .whereIn("creditcardid", usercardids)
                .get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val cardBenefit = document.toObject(CardBenefitsModel::class.java)
                            list.add(cardBenefit)
                            info(document)
                        }
                        myCallback.onCallback(list)
                    }
                }
        }
    }

    override fun create(arg: CardBenefitsModel) {
            documentdata
                .document(arg.id.toString())
                .set(arg)
        }

    override fun update(arg: CardBenefitsModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }
}


