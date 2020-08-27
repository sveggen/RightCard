package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardModel(
    var id: String?,
    var name: String?,
    var provider: String?
) : Parcelable {constructor() : this("", "", "")}
