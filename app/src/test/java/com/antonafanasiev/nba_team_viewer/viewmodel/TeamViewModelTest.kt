package com.antonafanasiev.nba_team_viewer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antonafanasiev.nba_team_viewer.model.Player
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.network.NetworkClient
import com.nhaarman.mockitokotlin2.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.IOException


class TeamViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val playersList1 =
        listOf(
            Player(0, "John", "Collins", "F-C", 20),
            Player(100, "Tyler", "Dorsey", "PG", 2)
        )
    private val playersList2 =
        listOf(
            Player(0, "Bradley", "Beal", "SG", 3),
            Player(100, "Tim", "Frazier", "G", 8)
        )
    private val playersList3 =
        listOf(
            Player(0, "Arron", "Afflalo", "G-F", 4),
            Player(100, "Khem", "Birch", "F-C", 24)
        )

    private val bestTeam = Team(0, 100, 0, "Dream Team", playersList1)
    private val worstTeam = Team(1, 1, 99, "Nightmare Team", playersList2)
    private val averageTeam = Team(2, 50, 50, "Average Team", playersList3)

    private val expectedUrl =
        "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/input.json"

    private val givenTeams = listOf(bestTeam, worstTeam, averageTeam)
    private val givenRequest = Request.Builder()
        .url("https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/input.json")
        .build()

    private val mockNetworkClient = mock<NetworkClient>()
    private val subject = TeamViewModel(mockNetworkClient)


    @Test
    fun `when fetchTeams respond with onSuccess then validate teams data is populated`() {
        //Given
        invokeOnSuccessResponse()

        //Expected
        val expectedTeamsListSortedAlphabetically = listOf(averageTeam, bestTeam, worstTeam)

        //When
        subject.fetchTeams()

        //Then
        val actualTeams = subject.teamsListLive.value
        val actualErrorMessage = subject.errorMessage.value

        verify(mockNetworkClient).request(eq(expectedUrl), any(), any())
        assertEquals(actualTeams, expectedTeamsListSortedAlphabetically)
        assertNull(actualErrorMessage)
    }

    @Test
    fun `when sort teams by ALPHABETICALLY then validate they sorted as expected`() {
        //Given
        invokeOnSuccessResponse()

        //Expected
        val expectedTeamsListSortedAlphabetically = listOf(averageTeam, bestTeam, worstTeam)


        //When
        subject.fetchTeams()
        subject.sortBy(SortingType.Alphabetical)

        //Then
        val actualTeams = subject.teamsListLive.value
        val actualErrorMessage = subject.errorMessage.value

        verify(mockNetworkClient).request(eq(expectedUrl), any(), any())
        assertEquals(actualTeams, expectedTeamsListSortedAlphabetically)
        assertNull(actualErrorMessage)
    }

    @Test
    fun `when sort teams by WINS then validate they sorted as expected`() {
        //Given
        invokeOnSuccessResponse()

        //Expected
        val expectedTeamsListSortedByWins = listOf(bestTeam, averageTeam, worstTeam)

        //When
        subject.fetchTeams()
        subject.sortBy(SortingType.Wins)

        //Then
        val actualTeams = subject.teamsListLive.value
        val actualErrorMessage = subject.errorMessage.value

        verify(mockNetworkClient).request(eq(expectedUrl), any(), any())
        assertNull(actualErrorMessage)
        assertEquals(actualTeams, expectedTeamsListSortedByWins)
    }

    @Test
    fun `when sort teams by Looses then validate they sorted as expected`() {
        //Given
        invokeOnSuccessResponse()

        //Expected
        val expectedTeamsListSortedByLosses = listOf(worstTeam, averageTeam, bestTeam)

        //When
        subject.fetchTeams()
        subject.sortBy(SortingType.Loses)

        //Then
        val actualTeams = subject.teamsListLive.value
        val actualErrorMessage = subject.errorMessage.value

        verify(mockNetworkClient).request(eq(expectedUrl), any(), any())
        assertNull(actualErrorMessage)
        assertEquals(actualTeams, expectedTeamsListSortedByLosses)
    }

    @Test
    fun `when fetchTeams respond with onFail then validate error data is populated`() {
        //Given
        invokeOnFailResponse()

        //Expected
        val expectedErrorMessage = "Ooops."

        //When
        subject.fetchTeams()
        subject.sortBy(SortingType.Alphabetical)

        //Then
        val actualTeams = subject.teamsListLive.value
        val actualErrorMessage = subject.errorMessage.value

        verify(mockNetworkClient).request(eq(expectedUrl), any(), any())
        assertNull(actualTeams)
        assertEquals(actualErrorMessage, expectedErrorMessage)
    }

    private fun invokeOnSuccessResponse() {
        val response = generateSuccessResponse()
        doAnswer {
            val callback = it.arguments[1] as (Response) -> Unit
            callback.invoke(response)
        }.`when`(mockNetworkClient).request(any(), any(), any())
    }

    private fun invokeOnFailResponse() {
        val exception = IOException("Ooops.")
        doAnswer {
            val callback = it.arguments[2] as (IOException) -> Unit
            callback.invoke(exception)
        }.`when`(mockNetworkClient).request(any(), any(), any())
    }

    private fun generateSuccessResponse(): Response {
        val jsonTeams = Json.encodeToJsonElement(givenTeams).toString()
        val responseBody = jsonTeams.toResponseBody()
        return Response.Builder().code(200).message("Any message").protocol(Protocol.HTTP_1_1)
            .body(responseBody).request(givenRequest).build()
    }
}
