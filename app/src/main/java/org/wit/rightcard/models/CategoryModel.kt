package org.wit.rightcard.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel (
    var id: String?,
    var type: String?

) : Parcelable