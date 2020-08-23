package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardModel(
    var uuid: String?,
    var name: String?
) : Parcelable {constructor() : this("", "")}
