package com.antonafanasiev.nba_team_viewer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Team(
    val id: Int,
    val wins: Int,
    val losses: Int,
    @SerialName("full_name") val fullName: String,
    val players: List<Player>
) : Parcelable

