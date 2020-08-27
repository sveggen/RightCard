package org.wit.rightcard.activities.items

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.mycards_listing.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.rightcard.R
import org.wit.rightcard.activities.NewCardActivity
import org.wit.rightcard.activities.UserCardActivity
import org.wit.rightcard.models.UserCardModel
import org.wit.rightcard.models.stores.UserCardStore

class UserCardItem(val userCreditcard: UserCardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.my_creditcard.text=userCreditcard.creditcardname
        viewHolder.itemView.creditcard_nickname.text=userCreditcard.nickname
        viewHolder.itemView.editNickname.setOnClickListener { editNickname() }
        viewHolder.itemView.deleteCreditCard.setOnClickListener { deleteCard() }
    }

    override fun getLayout(): Int {
        return R.layout.mycards_listing
    }

    private fun editNickname(){
        userCreditcard.nickname = "potet"
        UserCardStore().updateNickname(userCreditcard)
    }

    private fun deleteCard() {
        if (userCreditcard.id != null) {
            val userCardStore = UserCardStore()
            userCardStore.delete(userCreditcard.id!!)
        }
    }
    }
