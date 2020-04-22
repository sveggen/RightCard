package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
 class CreditCardModel(
    var uuid: String?,
    var name: String?
    ) : Parcelable {constructor() : this("", "")}
