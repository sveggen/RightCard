package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.models.*
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class CardBenefitsStore : Store<CardBenefitsModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("cardbenefits")

    override fun get(myCallback: Callback<CardBenefitsModel>) {
        TODO("Not yet implemented")
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


