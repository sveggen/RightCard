package org.wit.rightcard.activities.items

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.mycards_listing.view.*
import org.wit.rightcard.R
import org.wit.rightcard.models.UserCardModel

class UserCardItem(val userCreditcard: UserCardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.my_creditcard.text=userCreditcard.creditcardname
        viewHolder.itemView.creditcard_nickname.text=userCreditcard.nickname
    }

    override fun getLayout(): Int {
        return R.layout.mycards_listing
    }
}