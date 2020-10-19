package com.antonafanasiev.nba_team_viewer.network

import android.app.Application
import android.content.Context
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import java.io.File

class NetworkClientTest {

    private val mockApplicationContext = mock<Context>() {
        on { cacheDir } doReturn File("some/path")
    }
    private val mockApplication = mock<Application>() {
        on { applicationContext } doReturn mockApplicationContext
    }

    private val subject = NetworkClient(mockApplication)

    @Test
    fun `when request called, validate expected flow`() {
        //Given

        //When
        subject.request("https://someurl.com", {  }, {  })

        //Then

    }
}