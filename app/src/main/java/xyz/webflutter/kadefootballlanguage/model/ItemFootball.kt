package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemFootball(
    val id: String?,
    val name: String?,
    val description: String?,
    val logo: Int
) : Parcelable