package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
    var email: String?,
    var password: String?
) : Parcelable {constructor() : this( "", "")}