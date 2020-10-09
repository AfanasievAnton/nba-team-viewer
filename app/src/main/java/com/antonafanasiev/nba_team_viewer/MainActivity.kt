package com.antonafanasiev.nba_team_viewer

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import okhttp3.*
import java.io.IOException

const val URL = "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/input.json"

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val request: Request = Request.Builder()
                .url(URL)
                .build()
            client.newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("response: onFailure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val responseJson = response.body!!.string()
                        println("team value: $responseJson")
                    }
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}