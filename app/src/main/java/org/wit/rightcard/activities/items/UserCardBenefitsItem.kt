package org.wit.rightcard.activities.items

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.shop_search_results.view.*
import org.wit.rightcard.R
import org.wit.rightcard.models.UserCardBenefitsModel

class UserCardBenefitsItem(val userCardBenefitsModelv2: UserCardBenefitsModel): Item<ViewHolder>(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.creditcard_benefit.text=userCardBenefitsModelv2.usercard?.creditcardname
        viewHolder.itemView.benefit_name.text=userCardBenefitsModelv2.benefit?.conditions
        viewHolder.itemView.card_image_benefit.setImageResource(R.drawable.ic_7_bank_norwegian_kortet)
    }

    override fun getLayout(): Int {
        return R.layout.shop_search_results
    }
}
