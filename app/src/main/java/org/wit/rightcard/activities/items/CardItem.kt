package org.wit.rightcard.activities.items

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.card_listing_newcard.view.*
import org.wit.rightcard.R
import org.wit.rightcard.models.CardModel

class CardItem(val creditcard: CardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_new_creditcard.text=creditcard.name
    }

    override fun getLayout(): Int {
        return R.layout.card_listing_newcard
    }
}