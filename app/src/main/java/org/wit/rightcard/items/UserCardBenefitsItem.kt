package org.wit.rightcard.items

import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.shop_search_results.view.*
import org.wit.rightcard.R
import org.wit.rightcard.persistence.models.UserCardBenefitsModel

class UserCardBenefitsItem(val userCardBenefitsModel: UserCardBenefitsModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_benefit.text=userCardBenefitsModel.usercard?.creditcardname
        viewHolder.itemView.benefit_name.text=userCardBenefitsModel.benefit?.conditions
        viewHolder.itemView.card_image_benefit.setImageResource(R.drawable.ic_7_bank_norwegian_kortet)

        val uri = userCardBenefitsModel.usercard?.image
        val target = viewHolder.itemView.card_image_benefit
        Picasso.get().load(uri).into(target)

    }

    override fun getLayout(): Int {
        return R.layout.shop_search_results
    }
}
