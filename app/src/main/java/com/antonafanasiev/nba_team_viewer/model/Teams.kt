package com.antonafanasiev.nba_team_viewer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Team(
    val id: Int,
    val wins: Int,
    val losses: Int,
    @SerialName("full_name") val fullName: String,
    val players: List<Player>
)

@Serializable
data class Player(
    val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val position: String,
    val number: Int
)
