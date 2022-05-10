package com.antonafanasiev.nba_team_viewer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.model.Player
import com.antonafanasiev.nba_team_viewer.view.adapters.viewholders.PlayersViewHolder

class PlayersAdapter(private val playersList: List<Player>) :
    RecyclerView.Adapter<PlayersViewHolder>() {

    // Changes on "C" from dev
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.players_layout, parent, false)
        return PlayersViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
       holder.bindPlayer(playersList[position])
    }

    override fun getItemCount(): Int {
        return playersList.size
    }
}