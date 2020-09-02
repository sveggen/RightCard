package org.wit.rightcard.items

import android.graphics.Color
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.card_listing_newcard.view.*
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.CardModel
import org.wit.rightcard.persistence.models.UserCardModel
import org.wit.rightcard.persistence.stores.UserCardStore

/**
 * Item-class for CardModel. Used to populate RecyclerView.
 */
class CardItem(val creditcard: CardModel): Item<ViewHolder>(){

    private lateinit var auth: FirebaseAuth

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_new_creditcard.text=creditcard.name
        viewHolder.itemView.addCreditCard.setOnClickListener { addCard(viewHolder) }


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val target = viewHolder.itemView.card_image
        loadImage(target)
    }

    override fun getLayout(): Int {
        return R.layout.card_listing_newcard
    }

    /**
     * Adds card to Database on button-click.
     *
     */
    private fun addCard(viewHolder: ViewHolder){
        val newUserCard = UserCardModel(
            auth.uid + creditcard.id, creditcard.id, creditcard.name,
            "", auth.uid, creditcard.image)
        if (creditcard.id != null) {
            viewHolder.itemView.addCreditCard.text = "Added"
            viewHolder.itemView.addCreditCard.background.setTint(Color.GRAY)
            UserCardStore().create(newUserCard)
        }
    }

    /**
     * Loads image from Firebase Cloud Storage using Picasso into the RecyclerView.
     */
    private fun loadImage(target: ImageView){
        val uri = creditcard.image
        Picasso.get().load(uri).into(target)
    }
}
