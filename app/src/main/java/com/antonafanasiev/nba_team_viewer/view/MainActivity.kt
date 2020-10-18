package com.antonafanasiev.nba_team_viewer.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antonafanasiev.nba_team_viewer.R
import com.antonafanasiev.nba_team_viewer.network.NetworkClient
import com.antonafanasiev.nba_team_viewer.viewmodel.SortingType
import com.antonafanasiev.nba_team_viewer.viewmodel.TeamViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        createViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.sort_alphabetical -> {
                viewModel.sort(type = SortingType.Alphabetical)
                true
            }
            R.id.sort_by_wins -> {
                viewModel.sort(type = SortingType.Wins)
                true
            }
            R.id.sort_by_loses -> {
                viewModel.sort(type = SortingType.Loses)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createViewModel() {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TeamViewModel(
                    NetworkClient(application)
                ) as T
            }
        }
        viewModel = ViewModelProvider(this, factory).get(TeamViewModel::class.java)
    }
}