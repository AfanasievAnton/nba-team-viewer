package com.antonafanasiev.nba_team_viewer.view.adapters

import com.antonafanasiev.nba_team_viewer.model.Player
import org.junit.Test
import org.junit.Assert.assertEquals


class PlayersAdapterTest {
    private val player1 = Player(0, "John", "Doe", "C", 99)
    private val player2 = Player(0, "William", "Shakespeare", "F", 1)
    private val players = listOf(player1, player2)
    private val subject = PlayersAdapter(players)


    @Test
    fun `when itemCount called then return size of players list`() {
        //When
        val actualItemCount = subject.itemCount

        //Then
        assertEquals(players.size, actualItemCount)
    }


}

