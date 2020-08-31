package org.wit.rightcard.activities.items

import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.mycards_listing.view.*
import kotlinx.android.synthetic.main.mycards_listing.view.card_image
import org.wit.rightcard.R
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.stores.UserCardStore

class UserCardItem(val userCreditcard: UserCardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.my_creditcard.text=userCreditcard.creditcardname
        viewHolder.itemView.creditcard_nickname.text=userCreditcard.nickname
        viewHolder.itemView.editNickname.setOnClickListener { editNickname() }
        viewHolder.itemView.deleteCreditCard.setOnClickListener { deleteCard(viewHolder) }

        val uri = userCreditcard.image
        val target = viewHolder.itemView.card_image
        Picasso.get().load(uri).into(target)

    }

    override fun getLayout(): Int {
        return R.layout.mycards_listing
    }

    private fun editNickname(){
        userCreditcard.nickname = "potet"
        UserCardStore().updateNickname(userCreditcard)
    }

    private fun deleteCard(viewHolder: ViewHolder) {
        if (userCreditcard.id != null) {
            val userCardStore = UserCardStore()
            userCardStore.delete(userCreditcard.id!!)


        }
    }
}
