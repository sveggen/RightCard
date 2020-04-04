package org.wit.rightcard.models

data class UserModel (
    var id:String="",
    var email:String="",
    var cards : List<CreditCardModel> = listOf()
)