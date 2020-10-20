package com.antonafanasiev.nba_team_viewer.view.adapters.viewholders

import android.view.View
import android.widget.TextView
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.model.Player
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.assertTrue


class PlayersViewHolderTest {

    private val mockTvPlayerName = mock<TextView>()
    private val mockTvPlayerPosition = mock<TextView>()
    private val mockTvPlayerNumber = mock<TextView>()
    private val mockView = mock<View> {
        on { findViewById<TextView>(R.id.tv_player_name) } doReturn mockTvPlayerName
        on { findViewById<TextView>(R.id.tv_player_position) } doReturn mockTvPlayerPosition
        on { findViewById<TextView>(R.id.tv_player_number) } doReturn mockTvPlayerNumber
    }

    private val subject = PlayersViewHolder(mockView)


    @Test
    fun `when bindPlayer called, then verify expected values set to text views`() {
        //Given
        val givenPlayer = Player(0, "John", "Doe", "C", 99)
        val playerNameCaptor = argumentCaptor<String>()
        val playerPositionCaptor = argumentCaptor<String>()
        val playerNumberCaptor = argumentCaptor<String>()

        //Expected
        val expectedPlayerName = "John Doe"
        val expectedPlayerPosition = "C"
        val expectedPlayerNumber = "99"

        //When
        subject.bindPlayer(givenPlayer)

        //Then
        verify(mockView.findViewById<TextView>(R.id.tv_player_name)).text =
            playerNameCaptor.capture()
        verify(mockView.findViewById<TextView>(R.id.tv_player_position)).text =
            playerPositionCaptor.capture()
        verify(mockView.findViewById<TextView>(R.id.tv_player_number)).text =
            playerNumberCaptor.capture()

        assertTrue(playerNameCaptor.firstValue == expectedPlayerName)
        assertTrue(playerPositionCaptor.firstValue == expectedPlayerPosition)
        assertTrue(playerNumberCaptor.firstValue == expectedPlayerNumber)

    }
}