package org.wit.rightcard.items

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.mycards_listing.view.*
import kotlinx.android.synthetic.main.mycards_listing.view.card_image
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserCardModel
import org.wit.rightcard.persistence.stores.UserCardStore

/**
 * Item-class for UserCardModel. Used to populate RecyclerView.
 */
class UserCardItem(val userCreditcard: UserCardModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (!userCreditcard.nickname.isNullOrEmpty()) {
            viewHolder.itemView.creditcard_nickname.setText(userCreditcard.nickname)
        }
        viewHolder.itemView.my_creditcard.text = userCreditcard.creditcardname
        // calls the saveNickname()-function on "Done"-button click on the virtual keyboard.
        viewHolder.itemView.creditcard_nickname.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                viewHolder.itemView.creditcard_nickname.hideKeyboard()
                saveNickname(viewHolder)
                true
            } else {
                false
            }
        }
        val target = viewHolder.itemView.card_image
        loadImage(target)
    }

    override fun getLayout(): Int {
        return R.layout.mycards_listing
    }

    /**
     * Saves nickname to Database without the need to update the adapter.
     */
    private fun saveNickname(viewHolder : ViewHolder){
        val nickname = viewHolder.itemView.creditcard_nickname.text.toString()
        userCreditcard.nickname = nickname
        UserCardStore().updateNickname(userCreditcard)
    }

    /**
     * Loads image from Firebase Cloud Storage using Picasso into the RecyclerView.
     */
    private fun loadImage(target: ImageView){
        val uri = userCreditcard.image
        Picasso.get().load(uri).into(target)
    }

    /**
     * Hides the keyboard.
     */
    private fun View.hideKeyboard() {
        val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(windowToken, 0)
    }
}
