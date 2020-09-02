package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property shopid unique id of connected shop
 * @property benefitid unique id of connected benefit
 * @property creditcardid unique id of credit card
 */
@Parcelize
data class CardBenefitsModel(
    var id: String?,
    var shopid: String?,
    var benefitid: String?,
    var creditcardid: String?
) : Parcelable {constructor() : this("", "", "", "")}
