package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCardModel(
    var uuid: String?,
    var name: String?
) : Parcelable {constructor() : this("", "")}
