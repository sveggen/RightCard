package org.wit.rightcard.activities.items

import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.card_listing_newcard.view.*
import org.wit.rightcard.R
import org.wit.rightcard.models.CardModel
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.stores.UserCardStore

class CardItem(val creditcard: CardModel): Item<ViewHolder>(){

    private lateinit var auth: FirebaseAuth

    override fun bind(viewHolder: ViewHolder, position: Int) {
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        viewHolder.itemView.creditcard_new_creditcard.text=creditcard.name
        //viewHolder.itemView.card_image.setImageResource(R.drawable.ic_credit_card_black_24dp)
        viewHolder.itemView.addCreditCard.setOnClickListener { addCard() }

        val uri = creditcard.image
        val target = viewHolder.itemView.card_image
        Picasso.get().load(uri).into(target)
        }

    override fun getLayout(): Int {
        return R.layout.card_listing_newcard
    }

    private fun addCard(){
        val newUserCard = UserCardModel(
            auth.uid + creditcard.id, creditcard.id, creditcard.name,
            "", auth.uid)
        if (creditcard.id != null) {
            UserCardStore().create(newUserCard)
        }
    }
}
