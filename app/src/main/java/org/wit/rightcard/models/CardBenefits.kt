package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardBenefits(
    var id: String?,
    var shopid: String?,
    var benefitid: String?,
    var cardid: String?
) : Parcelable {constructor() : this("", "", "", "")}
