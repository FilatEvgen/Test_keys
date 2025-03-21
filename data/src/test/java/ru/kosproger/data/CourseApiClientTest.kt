package ru.kosproger.data

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CourseApiClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: CourseApiClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiClient = retrofit.create(CourseApiClient::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetchCourses returns correct course details`() = runBlocking {
        val mockResponse = """
        {
            "courses": [
                {
                    "id": "1",
                    "title": "Course 1",
                    "text": "Description 1",
                    "price": 100.0,
                    "rate": 4.5,
                    "startDate": "2025-03-20",
                    "hasLike": true,
                    "publishDate": "2025-03-01"
                }
            ]
        }
    """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))

        val response = apiClient.fetchCourses()

        assertEquals(1, response.courses.size)
        assertEquals("Course 1", response.courses[0].title)
        assertEquals("Description 1", response.courses[0].text)
        assertEquals(100.0, response.courses[0].price, 0.0)
        assertEquals(4.5f, response.courses[0].rate, 0.0f)
        assertEquals("2025-03-20", response.courses[0].startDate)
        assertTrue(response.courses[0].hasLike)
        assertEquals("2025-03-01", response.courses[0].publishDate)
    }
}