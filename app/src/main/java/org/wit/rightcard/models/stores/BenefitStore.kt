package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.models.BenefitModel
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class BenefitStore : Store<BenefitModel> {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    private val documentdata = firestore.collection("benefits")

    override fun getSingle(documentPath: String): BenefitModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<BenefitModel>) {
        TODO("Not yet implemented")
    }

    override fun create(arg: BenefitModel) {
        arg.id = randomId()
        documentdata
            .document(arg.id.toString())
            .set(arg)
    }


    override fun update(arg: BenefitModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }
}