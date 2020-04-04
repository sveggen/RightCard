package org.wit.rightcard.models

class UserJSON (
    var id:String="",
    var email:String="",
    var cards : List<CreditCardModel> = listOf()
)