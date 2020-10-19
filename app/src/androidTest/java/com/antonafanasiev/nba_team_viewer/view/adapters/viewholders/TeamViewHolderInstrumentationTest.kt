package com.antonafanasiev.nba_team_viewer.view.adapters.viewholders

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.antonafanasiev.nba_team_viewer.databinding.TeamLayoutBinding
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.TeamsAdapter
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue


@RunWith(AndroidJUnit4ClassRunner::class)
class TeamViewHolderInstrumentationTest {

    private lateinit var teamLayoutBinding: TeamLayoutBinding

    private lateinit var subject: TeamViewHolder


    @Before
    fun setUp() {
        Handler(Looper.getMainLooper()).post {
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val inflater = LayoutInflater.from(appContext)
            teamLayoutBinding = TeamLayoutBinding.inflate(inflater)
            subject = TeamViewHolder(teamLayoutBinding)
        }
    }

    @Test
    fun validateViewBindingValuesSetOnTextViews() {
        //Given
        val givenTeam = Team(0, 100, 50, "Dream Team", emptyList())
        val givenTeamClickListener = object : TeamsAdapter.OnTeamClickListener {
            override fun onClick(team: Team) {
            }
        }

        //Expected
        val expectedTeamName = "Dream Team"
        val expectedWinsText = "100"
        val expectedLossesText = "50"

        //When
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        subject.onBind(givenTeam, givenTeamClickListener)
        teamLayoutBinding.teamContainer.performClick()
        //Then
        assertTrue(teamLayoutBinding.teamName.text == expectedTeamName)
        assertTrue(teamLayoutBinding.wins.text == expectedWinsText)
        assertTrue(teamLayoutBinding.loses.text == expectedLossesText)
    }

    @Test
    fun validateOnTeamClickListenerDispatchExpectedTeam() {
        //Expected
        var expectedTeam = Team(0, 0, 0, "", emptyList())

        //Given
        val givenTeam = Team(0, 100, 50, "Dream Team", emptyList())
        val givenTeamClickListener = object : TeamsAdapter.OnTeamClickListener {
            override fun onClick(team: Team) {
                expectedTeam = team
            }
        }

        //When
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        subject.onBind(givenTeam, givenTeamClickListener)
        teamLayoutBinding.teamContainer.performClick()

        //Then
        assertSame(expectedTeam, givenTeam)
    }

}