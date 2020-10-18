package com.antonafanasiev.nba_team_viewer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonafanasiev.nba_team_viewer.databinding.TeamLayoutBinding
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.viewholders.TeamViewHolder

class TeamsAdapter(private val onTeamClickListener: OnTeamClickListener) :
    RecyclerView.Adapter<TeamViewHolder>() {
    private var teamsList: List<Team> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = TeamLayoutBinding.inflate(inflater, parent, false)
        return TeamViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.onBind(teamsList[position], onTeamClickListener)
    }

    override fun getItemCount() = teamsList.size

    fun update(teamsList: List<Team>) {
        this.teamsList = teamsList
        notifyDataSetChanged()
    }

    interface OnTeamClickListener {
        fun onClick(team: Team)
    }
}