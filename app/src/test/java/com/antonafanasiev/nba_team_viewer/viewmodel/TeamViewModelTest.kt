package com.antonafanasiev.nba_team_viewer.viewmodel

import com.antonafanasiev.nba_team_viewer.network.NetworkClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test


class TeamViewModelTest {

    private val mockNetworkClient = mock<NetworkClient>()
    private val subject = TeamViewModel(mockNetworkClient)

    @Test
    fun `when fetchTeams respond with onSuccess`() {
        subject.fetchTeams()
    }
}