package org.wit.rightcard.models.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.models.BenefitModel
import org.wit.rightcard.models.CardBenefitsModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class BenefitStore : Store<BenefitModel> {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("benefits")


    override fun get(myCallback: Callback<BenefitModel>) {
        TODO("Not yet implemented")
    }

    fun query(benefitIdList: ArrayList<String>, myCallback: Callback<BenefitModel>){
        if (benefitIdList.isNotEmpty()) {
            //get all the benefit conditions
            val benefitList = ArrayList<BenefitModel>()
            firestore.collection("benefits")
                .whereIn("id", benefitIdList)
                .get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val benefit = document.toObject(BenefitModel::class.java)
                            benefitList.add(benefit)
                        }
                        myCallback.onCallback(benefitList)
                    }
                }
        }
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