package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.models.CardBenefitsModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class CardBenefitsStore : Store<CardBenefitsModel> {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private val documentdata = firestore.collection("cardbenefits")

    override fun getSingle(documentPath: String): CardBenefitsModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<CardBenefitsModel>) {
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