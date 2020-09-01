package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property conditions description of the benefit
 * @property category category that benefit falls into
 * @property discount benefit displayed as a number with or without a %
 */
@Parcelize
data class BenefitModel (
    var id: String?,
    var conditions: String?,
    var category: String?,
    var discount: String?
) : Parcelable {constructor() : this("", "", "", "")}