package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property email email/username of user
 * @property password password of user
 */
@Parcelize
data class UserModel (
    var email: String?,
    var password: String?
) : Parcelable {constructor() : this( "", "")}