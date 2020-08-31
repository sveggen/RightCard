package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCardBenefitsModelv2(
    var id: String?,
    var userid: String?,
    var shop: ShopModel?,
    var usercard: UserCardModel?,
    var benefit: BenefitModel?,
    var cardbenefit: CardBenefitsModel
) : Parcelable {constructor() : this("", "", ShopModel("", ""), UserCardModel("", "", "", "", ""), BenefitModel("", "", "", ""), CardBenefitsModel("", "", "", ""))}
