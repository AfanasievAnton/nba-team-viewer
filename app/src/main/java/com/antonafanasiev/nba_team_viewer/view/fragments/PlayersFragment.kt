package com.antonafanasiev.nba_team_viewer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.model.Player
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.PlayersAdapter
import kotlinx.android.synthetic.main.players_fragment.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PlayersFragment : Fragment() {

    // another foo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val team = arguments?.get("team") as Team
        updateTextViews(team)
        setupAdapter(team.players)
    }

    private fun updateTextViews(team: Team) {
        tv_team_name.text = team.fullName
        tv_wins.text = getString(R.string.team_wins_placeholder, team.wins)
        tv_loses.text = getString(R.string.team_losses_placeholder, team.losses)
    }

    private fun setupAdapter(playersList: List<Player>) {
        rv_players.apply {
            layoutManager = GridLayoutManager(activity, 1)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = PlayersAdapter(playersList)
        }
    }
}