package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property creditcardid unique id of credit card
 * @property creditcardname name of credit card
 * @property nickname nickname of credit card
 * @property userid unique auth.uid
 * @property image link to image of credit card
 */
@Parcelize
data class UserCardModel(
    var id: String?,
    var creditcardid: String?,
    var creditcardname: String?,
    var nickname: String?,
    var userid: String?,
    var image: String?
) : Parcelable {constructor() : this("","", "", "", "", "")}