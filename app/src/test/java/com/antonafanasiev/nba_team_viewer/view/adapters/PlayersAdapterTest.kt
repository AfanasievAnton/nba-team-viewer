package com.antonafanasiev.nba_team_viewer.view.adapters

import com.antonafanasiev.nba_team_viewer.model.Player
import com.antonafanasiev.nba_team_viewer.view.adapters.viewholders.PlayersViewHolder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.same
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.assertEquals


class PlayersAdapterTest {
    private val player1 = Player(0, "John", "Doe", "C", 99)
    private val player2 = Player(0, "William", "Shakespeare", "F", 1)
    private val players = listOf(player1, player2)
    private val subject = PlayersAdapter(players)

    @Test
    fun `when bindViewHolder() called then bind expected player object`() {
        //Given
        val givenMockViewHolder = mock<PlayersViewHolder>()

        //When
        subject.bindViewHolder(mock(), 1)

        //Then
        verify(givenMockViewHolder).bindPlayer(same(players[1]))
    }

    @Test
    fun `when itemCount called then return size of players list`() {
        //When
        val actualItemCount = subject.itemCount

        //Then
        assertEquals(players.size, actualItemCount)
    }


}

