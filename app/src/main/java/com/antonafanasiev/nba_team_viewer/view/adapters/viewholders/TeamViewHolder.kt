package com.antonafanasiev.nba_team_viewer.view.adapters.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.antonafanasiev.nba_team_viewer.BR
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.TeamsAdapter

class TeamViewHolder constructor(
    private val dataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(team: Team, onTeamClickListener: TeamsAdapter.OnTeamClickListener) {
        dataBinding.setVariable(BR.teamData, team)
        dataBinding.root.setOnClickListener{ onTeamClickListener.onClick(team)}
        dataBinding.executePendingBindings()
    }
}