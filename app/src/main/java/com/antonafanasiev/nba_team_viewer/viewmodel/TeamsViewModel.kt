package com.antonafanasiev.nba_team_viewer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.network.NetworkClient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException


private const val URL =
    "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/input.json"

class TeamViewModel(private val client: NetworkClient) : ViewModel() {

    private var preferredSortType: SortingType = SortingType.Alphabetical
    val teamsListLive = MutableLiveData<List<Team>>()
    val errorMessage = MutableLiveData<String>()


    fun fetchTeams() = client.request(URL, { onSuccess(it) }, { onFail(it) })

    private fun onSuccess(response: Response) {
        val teams: List<Team> =
            response.body!!.string().let { Json.decodeFromString(it) }
        sort(teams, preferredSortType)
    }

    private fun onFail(e: IOException) {
        errorMessage.postValue(e.message)
    }

    fun sort(team: List<Team>? = teamsListLive.value, type: SortingType) {
        preferredSortType = type
        val sortedTeams = when (preferredSortType) {
            SortingType.Alphabetical -> team?.sortedBy { it.fullName }
            SortingType.Wins -> team?.sortedByDescending { it.wins }
            SortingType.Loses -> team?.sortedByDescending { it.losses }
        }

        sortedTeams?.let {
            teamsListLive.postValue(it)
        }
    }
}


enum class SortingType {
    Alphabetical,
    Wins,
    Loses
}
