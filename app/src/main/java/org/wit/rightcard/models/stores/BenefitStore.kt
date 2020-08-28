package org.wit.rightcard.models.stores

import org.wit.rightcard.models.BenefitModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class BenefitStore : Store<BenefitModel> {
    override fun getSingle(documentPath: String): BenefitModel {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<BenefitModel>) {
        TODO("Not yet implemented")
    }

    override fun create(arg: BenefitModel) {
        TODO("Not yet implemented")
    }

    override fun update(arg: BenefitModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }
}