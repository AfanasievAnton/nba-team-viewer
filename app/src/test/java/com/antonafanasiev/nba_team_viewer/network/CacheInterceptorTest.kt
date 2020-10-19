package com.antonafanasiev.nba_team_viewer.network

import com.nhaarman.mockitokotlin2.*
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CacheInterceptorTest {

    private val subject = CacheInterceptor()


    @Test
    fun `when intercept execute validate expected Response`() {
        //Given
        val givenRequest = Request.Builder().url("http://someurl.com").build()
        val givenResponse =
            Response.Builder().code(200).message("Any message").protocol(Protocol.HTTP_1_1)
                .request(givenRequest).build()
        val mockChain = mock<Interceptor.Chain> {
            on { proceed(any()) } doReturn givenResponse
            on { request() } doReturn givenRequest
        }

        //Expected
        val expectedCacheControl = "max-age=900"

        //When
        val response = subject.intercept(mockChain)

        //Then
        val order = inOrder(mockChain)
        order.verify(mockChain).request()
        order.verify(mockChain).proceed(any())
        verifyNoMoreInteractions(mockChain)

        assertEquals(response.header("Cache-Control"), expectedCacheControl)
        assertNull(response.header("Pragma"))
    }
}