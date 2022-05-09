package com.antonafanasiev.nba_team_viewer.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.databinding.TeamsFragmentBinding
import com.antonafanasiev.nba_team_viewer.model.Team
import com.antonafanasiev.nba_team_viewer.view.adapters.TeamsAdapter
import com.antonafanasiev.nba_team_viewer.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.teams_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TeamsFragment : Fragment(), TeamsAdapter.OnTeamClickListener {

    // From feature branch
    private lateinit var teamsDataBinding: TeamsFragmentBinding
    private lateinit var adapter: TeamsAdapter
    private val viewModel: TeamViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teamsDataBinding = TeamsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = this@TeamsFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return teamsDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamsDataBinding.viewmodel?.fetchTeams()
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        teamsDataBinding.viewmodel?.apply {
            adapter = TeamsAdapter(this@TeamsFragment)
            teams_recycleview.apply {
                layoutManager = LinearLayoutManager(activity)
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = this@TeamsFragment.adapter
            }
        }
    }

    private fun setupObservers() {
        teamsDataBinding.viewmodel?.teamsListLive?.observe(viewLifecycleOwner, {
            adapter.update(it)
        })
        teamsDataBinding.viewmodel?.errorMessage?.observe(viewLifecycleOwner, {
            teams_recycleview.visibility = View.GONE
            ll_teams_raw_cotainer.visibility = View.GONE
            tv_leaderboard.visibility = View.GONE
            tv_error_message.visibility = View.VISIBLE
            tv_error_message.text = it
        })
    }

    override fun onClick(team: Team) {
        val bundle = bundleOf("team" to team)
        findNavController().navigate(R.id.action_TeamsFragment_to_PlayersFragment, bundle)
    }
}