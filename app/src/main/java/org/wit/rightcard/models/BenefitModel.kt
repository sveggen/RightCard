package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BenefitModel (
    var id: String?,
    var conditions: String?,
    var category: String?,
    var discount: String?
) : Parcelable {constructor() : this("", "", "", "")}