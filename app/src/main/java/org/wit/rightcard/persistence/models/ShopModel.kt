package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property name name of shop
 */
@Parcelize
data class ShopModel (
    var id: String?,
    var name: String?
) : Parcelable {constructor() : this("","")}