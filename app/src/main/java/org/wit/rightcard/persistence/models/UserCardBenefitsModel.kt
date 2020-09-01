package org.wit.rightcard.persistence.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @property id unique id
 * @property userid unique auth.uid of user
 * @property shop connected ShopModel-object
 * @property usercard connected UserCardModel-object
 * @property benefit connected BenefitModel-object
 * @property cardbenefit connected CardBenefitsModel-object
 */
@Parcelize
data class UserCardBenefitsModel(
    var id: String?,
    var userid: String?,
    var shop: ShopModel?,
    var usercard: UserCardModel?,
    var benefit: BenefitModel?,
    var cardbenefit: CardBenefitsModel
) : Parcelable {constructor() : this("", "", ShopModel(), UserCardModel(), BenefitModel(), CardBenefitsModel())}
