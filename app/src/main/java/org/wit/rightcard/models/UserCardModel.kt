package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCardModel(
    var uuid: String?,
    var creditcarduuid: String?,
    var creditcardname: String?,
    var nickname: String?,
    var useruuid: String?
) : Parcelable {constructor() : this("","", "", "", "")}