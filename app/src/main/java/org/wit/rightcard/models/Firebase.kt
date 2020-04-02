package org.wit.rightcard.models

interface Firebase {

    fun onFirebaseLoadSuccess(creditcardList:List<CreditCardModel>)
    fun onFirebaseLoadFailed(message:String)

}