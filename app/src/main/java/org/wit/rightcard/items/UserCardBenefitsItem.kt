package org.wit.rightcard.items

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.shop_search_results.view.*
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserCardBenefitsModel

/**
 * Item-class for UserCardBenefitsModel. Used to populate RecyclerView.
 */
class UserCardBenefitsItem(private val userCardBenefitsModel: UserCardBenefitsModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_benefit.text=userCardBenefitsModel.usercard?.creditcardname
        viewHolder.itemView.benefit_name.text=userCardBenefitsModel.benefit?.conditions
        viewHolder.itemView.card_image_benefit.setImageResource(R.drawable.ic_7_bank_norwegian_kortet)
        viewHolder.itemView.creditcard_benefit_nickname.hint = userCardBenefitsModel.usercard?.nickname

        val target = viewHolder.itemView.card_image_benefit
        loadImage(target)
    }

    override fun getLayout(): Int {
        return R.layout.shop_search_results
    }


    /**
     * Loads image from Firebase Cloud Storage using Picasso into the RecyclerView.
     */
    private fun loadImage(target: ImageView){
        val uri = userCardBenefitsModel.usercard?.image
        Picasso.get().load(uri).into(target)
    }
}
