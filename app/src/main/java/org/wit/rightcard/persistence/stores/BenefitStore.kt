package org.wit.rightcard.persistence.stores

import com.google.firebase.firestore.FirebaseFirestore
import org.wit.rightcard.helpers.randomId
import org.wit.rightcard.persistence.models.BenefitModel
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.interfaces.Store

/**
 * Handles all database calls for BenefitModel.
 */
class BenefitStore : Store<BenefitModel> {

    private val firestore = FirebaseFirestore.getInstance()
    private val documentdata = firestore.collection("benefits")

    /**
     * Retrieves all benefits that matches any of the benefits in the list provided as argument.
     */
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

    override fun get(myCallback: Callback<BenefitModel>) {
        TODO("Not yet implemented")
    }

    override fun update(arg: BenefitModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        documentdata
            .document(documentPath)
            .delete()
    }
}