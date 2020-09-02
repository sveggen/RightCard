package org.wit.rightcard.helpers

import org.wit.rightcard.persistence.models.BenefitModel
import org.wit.rightcard.persistence.models.CardBenefitsModel
import org.wit.rightcard.persistence.models.CardModel
import org.wit.rightcard.persistence.models.ShopModel
import org.wit.rightcard.persistence.stores.BenefitStore
import org.wit.rightcard.persistence.stores.CardBenefitsStore
import org.wit.rightcard.persistence.stores.CardStore
import org.wit.rightcard.persistence.stores.ShopStore

/**
 * Contains functions to generate data that is not generated in other ways in the app.
 */


fun createBenefit(){
    val benefitModel = BenefitModel("", "5% Trumf-bonus on mondays", "Grocery", "5%")
    BenefitStore().create(benefitModel)
}

fun createCardBenefit(){
    val shopid = "51672797-153b-4cea-be57-5bb7431c538a"
    val benefitid = "40708937-0c2a-408a-8003-8f7fa9dec039"
    val creditcardid = "720f3839-fd89-416c-9537-83a28e5899d1"
    val cardBenefitModel = CardBenefitsModel(shopid + benefitid + creditcardid, shopid, benefitid, creditcardid)
    CardBenefitsStore().create(cardBenefitModel)
}

fun createStore(){
    val shopModel = ShopModel("", "")
    ShopStore().create(shopModel)
}

fun createCard(){
    val cardModel = CardModel()
    CardStore().create(cardModel)
}