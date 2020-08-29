package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCardBenefitsModel(
    var id: String?,
    var userid: String?,
    var shopid: String?,
    var cardname: String?,
    var benefit: BenefitModel,
    var creditcardid: String?
) : Parcelable {constructor() : this( "", "", "", "", BenefitModel("", "", "", ""), "")}
