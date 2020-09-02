package org.wit.rightcard.persistence.stores

import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.persistence.interfaces.Callback
import org.wit.rightcard.persistence.interfaces.SingleCallback
import org.wit.rightcard.persistence.interfaces.Store
import org.wit.rightcard.persistence.models.*

/**
 * Handles the retrieval of data from various other Stores - respectively:
 * ShopModel, UserCardModel, CardBenefitsModel, BenefitModel and stores said
 * data in a UserCardBenefitsModel locally.
 */
class UserCardBenefitsStore : Store<UserCardBenefitsModel>, AnkoLogger {

    /**
     * Query that retrieves the benefit that each of the users cards provide for a single shop,
     * that is provided as a String.
     */
    fun getAll(enteredText: String, myCallback: Callback<UserCardBenefitsModel>){
        val userBenefits = ArrayList<UserCardBenefitsModel>()
        val creditCardId = ArrayList<String>()

        //get the shop object that corresponds to the variable "nickname" and stores it
        var shop: ShopModel
        ShopStore().query(enteredText, object: SingleCallback<ShopModel> {
        override fun onCallback(arg: ShopModel) {
            shop = arg

            //get all the users cards
            UserCardStore().get(object: Callback<UserCardModel> {
                override fun onCallback(list: List<UserCardModel>) {
                    if (list.isNotEmpty()){
                        for (card in list) {
                            val userCardBenefitsModelv2 = UserCardBenefitsModel()
                            creditCardId.add(card.creditcardid.toString())
                            userCardBenefitsModelv2.usercard = card
                            userBenefits.add(userCardBenefitsModelv2)
                        }
                    }

                    //get all cardbenefits
                    CardBenefitsStore().query(creditCardId, object: Callback<CardBenefitsModel>{
                        override fun onCallback(list: List<CardBenefitsModel>){
                            for (benefit in list) {
                                        for (userBenefit in userBenefits) {
                                            if (userBenefit.shop?.name != shop.name) {
                                            }
                                            if (userBenefit.usercard?.creditcardid == benefit.creditcardid &&  shop.id == benefit.shopid) {
                                                userBenefit.cardbenefit = benefit
                                            }
                                    }
                            }
                            val benefitIdList = ArrayList<String>()
                            for (userBenefit in userBenefits) {
                                benefitIdList.add(userBenefit.cardbenefit.benefitid.toString())
                            }

                            //get all benefits
                            BenefitStore().query(benefitIdList, object: Callback<BenefitModel>{
                                override fun onCallback(list: List<BenefitModel>){
                                    for (benefit in list) {
                                        for (userBenefit in userBenefits) {
                                            if (userBenefit.cardbenefit.benefitid.toString() == benefit.id) {
                                                userBenefit.benefit = benefit
                                                userBenefit.shop = shop
                                            }
                                        }
                                    }
                                    myCallback.onCallback(userBenefits)
                                }
                            })
                        }
                    })

                }
            })
            //return empty list
            if (userBenefits.isEmpty()) {
                myCallback.onCallback(userBenefits)
            }
        }
        })
    }

    override fun get(myCallback: Callback<UserCardBenefitsModel>) {
        TODO("Not yet implemented")
    }

    override fun create(arg: UserCardBenefitsModel) {
        TODO("Not yet implemented")
    }

    override fun update(arg: UserCardBenefitsModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }

}



