package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property name the provider + name of the card
 * @property provider bank-provider of credit card
 * @property image link to image of card
 */
@Parcelize
data class CardModel(
    var id: String?,
    var name: String?,
    var provider: String?,
    var image: String?
) : Parcelable {constructor() : this("", "", "", "")}
