package org.wit.rightcard.models.stores

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.rightcard.models.*
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.SingleCallback
import org.wit.rightcard.models.interfaces.Store

class UserCardBenefitsStore : Store<CardBenefitsModel>, AnkoLogger {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
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

    fun getAll(shop : String, myCallback: Callback<UserCardBenefitsModel>) {
        auth = FirebaseAuth.getInstance()
        //get shop id
        firestore.collection("shops")
            .whereIn("name", listOf(shop))
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var shopid = ""
                    val usercardids = ArrayList<String>()
                    val userBenefits = ArrayList<UserCardBenefitsModel>()
                    for (document in task.result!!) {
                        shopid = document["id"].toString()
                    }

                    //get all owned credit cards
                    firestore.collection("ownedcreditcards")
                        .whereIn("userid", listOf(auth.uid))
                        .get().addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                for (document in task2.result!!) {
                                    val creditcardid = document.get("creditcardid").toString()
                                    usercardids.add(creditcardid)
                                    val creditCardName = document.get("creditcardname").toString()
                                    info(creditcardid)
                                }

                                if (usercardids.isNotEmpty()){
                                    //get all the cards benefits
                                    firestore.collection("cardbenefits")
                                        .whereIn("creditcardid", usercardids)
                                        .get().addOnCompleteListener { task3 ->
                                            if (task3.isSuccessful) {
                                                for (document in task3.result!!) {
                                                    info(document)
                                                    val userBenefit = UserCardBenefitsModel(
                                                        "",
                                                        auth.uid,
                                                        document.get("shopid").toString(),
                                                        "name",
                                                        BenefitModel(
                                                            document.get("benefitid").toString(), "", "", ""
                                                        ),
                                                        document.get("creditcardid").toString()
                                                    )
                                                    if (document.get("shopid").toString() == shopid) {
                                                        userBenefits.add(userBenefit)
                                                    }
                                                }
                                                val benefitIdList = ArrayList<String>()
                                                for (userBenefit in userBenefits) {
                                                    benefitIdList.add(userBenefit.benefit.id.toString())
                                                }

                                                if (benefitIdList.isNotEmpty()) {
                                                    //get all the benefit conditions
                                                    firestore.collection("benefits")
                                                        .whereIn("id", benefitIdList)
                                                        .get().addOnCompleteListener { task4 ->
                                                            if (task4.isSuccessful) {
                                                                for (document in task4.result!!) {
                                                                    val benefit = document.toObject(BenefitModel::class.java)
                                                                    for (userBenefit in userBenefits) {
                                                                        if (userBenefit.benefit.id.toString() == benefit.id) {
                                                                            userBenefit.benefit = benefit
                                                                        }
                                                                    }
                                                                }
                                                                myCallback.onCallback(userBenefits)
                                                            }
                                                        }
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    if (userBenefits.isEmpty()){
                        myCallback.onCallback(userBenefits)
                    }
                }
            }

    }

    fun getAllv2(myCallback: Callback<UserCardBenefitsModelv2>){
        val userBenefits = ArrayList<UserCardBenefitsModelv2>()
        val creditCardId = ArrayList<String>()

        //get the shop object that corresponds to the variable "nickname" and stores it
        var shop: ShopModel
        ShopStore().query("Rema 1000", object: SingleCallback<ShopModel> {
        override fun onCallback(arg: ShopModel) {
            shop = arg

            //get all the users cards
            UserCardStore().get(object: Callback<UserCardModel> {
                override fun onCallback(list: List<UserCardModel>) {
                    if (list.isNotEmpty()){
                        for (card in list) {
                            val userCardBenefitsModelv2 = UserCardBenefitsModelv2()
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
        }
        })
    }
}



