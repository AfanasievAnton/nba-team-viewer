package com.antonafanasiev.nba_team_viewer.view.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.model.Player

class PlayersViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {

    fun bindPlayer(player: Player) {
        itemView.findViewById<TextView>(R.id.tv_player_name).text =
            "${player.firstName} ${player.lastName}"

        itemView.findViewById<TextView>(R.id.tv_player_position).text =
            player.position

        itemView.findViewById<TextView>(R.id.tv_player_number).text =
            player.number.toString()
    }
}