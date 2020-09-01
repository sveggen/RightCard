package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardBenefitsModel(
    var id: String?,
    var shopid: String?,
    var benefitid: String?,
    var creditcardid: String?
) : Parcelable {constructor() : this("", "", "", "")}
