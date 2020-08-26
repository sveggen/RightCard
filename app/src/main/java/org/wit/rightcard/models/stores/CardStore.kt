package org.wit.rightcard.models.stores

import org.jetbrains.anko.AnkoLogger
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.interfaces.Callback
import org.wit.rightcard.models.interfaces.Store

class CardStore : Store<CardModel>, AnkoLogger {

    override fun update(arg: CardModel) {
        TODO("Not yet implemented")
    }

    override fun delete(documentPath: String) {
        TODO("Not yet implemented")
    }

    override fun getSingle(documentPath: String): CardModel {
        TODO("Not yet implemented")
    }

    override fun create(arg: CardModel) {
        TODO("Not yet implemented")
    }

    override fun getAll(myCallback: Callback<CardModel>) {
        TODO("Not yet implemented")
    }



}