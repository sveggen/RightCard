package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCardModel(
    var id: String?,
    var creditcardid: String?,
    var creditcardname: String?,
    var nickname: String?,
    var userid: String?,
    var image: String?
) : Parcelable {constructor() : this("","", "", "", "", "")}