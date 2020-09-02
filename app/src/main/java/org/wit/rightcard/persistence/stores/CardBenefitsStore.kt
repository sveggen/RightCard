package org.wit.rightcard.persistence.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.interfaces.Store
import org.wit.rightcard.persistence.models.CardBenefitsModel

/**
 * Handles all database calls for CardBenefitsModel.
 */
class CardBenefitsStore : Store<CardBenefitsModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("cardbenefits")

    /**
     * Returns all the user cards if they match any of the lists provided in the query.
     *
     */
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

    override fun delete(documentPath: String) {
        documentdata
            .document(documentPath)
            .delete()
    }

    override fun get(myCallback: Callback<CardBenefitsModel>) {
        TODO("Not yet implemented")
    }

    override fun update(arg: CardBenefitsModel) {
        TODO("Not yet implemented")
    }
}


