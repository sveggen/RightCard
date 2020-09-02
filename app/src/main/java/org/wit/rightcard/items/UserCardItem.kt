package org.wit.rightcard.items

import android.view.View
import android.view.inputmethod.EditorInfo
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.mycards_listing.view.*
import kotlinx.android.synthetic.main.mycards_listing.view.card_image
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserCardModel
import org.wit.rightcard.persistence.stores.UserCardStore

class UserCardItem(val userCreditcard: UserCardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (!userCreditcard.nickname.isNullOrEmpty()) {
            viewHolder.itemView.creditcard_nickname.setText(userCreditcard.nickname)
        }
        viewHolder.itemView.my_creditcard.text = userCreditcard.creditcardname
        //viewHolder.itemView.creditcard_nickname.setImeActionLabel("Save", KeyEvent.KEYCODE_ENTER);
        viewHolder.itemView.creditcard_nickname.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                saveNickname(viewHolder)
                true
            } else {
                false
            }
        }

        val uri = userCreditcard.image
        val target = viewHolder.itemView.card_image
        Picasso.get().load(uri).into(target)

    }

    override fun getLayout(): Int {
        return R.layout.mycards_listing
    }

    private fun saveNickname(viewHolder : ViewHolder){
        val nickname = viewHolder.itemView.creditcard_nickname.text.toString()
        userCreditcard.nickname = nickname
        UserCardStore().updateNickname(userCreditcard)
    }

    private fun editNickname(viewHolder: ViewHolder){
        viewHolder.itemView.editNickname.visibility = View.VISIBLE
    }
}
