package com.antonafanasiev.nba_team_viewer.view.adapters.viewholders

import android.view.View
import com.antonafanasiev.nba_team_viewer.BR
import com.antonafanasiev.nba_team_viewer.databinding.TeamLayoutBinding
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.TeamsAdapter
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class TeamViewHolderTest {

    private val mockRootView = mock<View>()
    private val mockTeamLayoutBinding = mock<TeamLayoutBinding> {
        on { root } doReturn mockRootView
    }

    private val subject = TeamViewHolder(mockTeamLayoutBinding)

    @Test
    fun `when onBind called validate Team object values are respected and methods called in order`(){
        //Given
        val givenTeam = Team(0, 100, 50, "Dream Team", emptyList())
        val givenOnTeamClickListener = mock<TeamsAdapter.OnTeamClickListener>()

        //When
        subject.onBind(givenTeam, givenOnTeamClickListener)

        //Then
        val order = inOrder(mockTeamLayoutBinding, mockRootView)
        order.verify(mockTeamLayoutBinding).setVariable(eq(BR.teamData), same(givenTeam))
        order.verify(mockRootView).setOnClickListener(any())
        order.verify(mockTeamLayoutBinding).executePendingBindings()
    }
}
