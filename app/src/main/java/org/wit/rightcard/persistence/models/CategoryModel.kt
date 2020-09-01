package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel (
    var id: String?,
    var type: String?

) : Parcelable {constructor() : this( "", "")}