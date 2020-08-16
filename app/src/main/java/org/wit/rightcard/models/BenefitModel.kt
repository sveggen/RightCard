package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BenefitModel (
    var uuid: String?,
    var conditions: String?,
    var category: String?,
    var creditcarduuid: String?
) : Parcelable