package com.beyazid.perform

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasource
import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasourceImp
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepositoryImp
import com.beyazid.perform.data.model.latests_news.Enclosure
import com.beyazid.perform.data.model.latests_news.LatestNewsItem
import com.beyazid.perform.data.model.latests_news.LatestNewsResponse
import com.beyazid.perform.data.network.ApiService
import com.beyazid.perform.ui.latest_news.LatestNewsFragment
import com.beyazid.perform.ui.latest_news.LatestNewsViewModel
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

/**
 *  Created by beyazid on 2019-05-25.
 */
@ExperimentalCoroutinesApi
class LatestNewsTest {
    @UseExperimental(ObsoleteCoroutinesApi::class)
    val mainThreadSurrogate = newSingleThreadContext("UI")

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @MockK(relaxed = true)
    lateinit var apiService: ApiService

    @MockK(relaxed = true)
    lateinit var gson: Gson

    @MockK(relaxed = true)
    lateinit var newsDatasource: LatestNewsDatasource

    @MockK(relaxed = true)
    lateinit var newsRepository: LatestNewsRepository

    @MockK(relaxed = true)
    lateinit var newsFragment: LatestNewsFragment

    @MockK(relaxed = true)
    lateinit var newsResponse: LatestNewsResponse

    lateinit var newsRepositoryImp: LatestNewsRepositoryImp
    lateinit var newsDatasourceImp: LatestNewsDatasourceImp
    lateinit var newsViewModel: LatestNewsViewModel
    lateinit var latestNewsItem: LatestNewsItem
    lateinit var enclosure: Enclosure
    lateinit var newsList: List<LatestNewsItem>
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        newsDatasourceImp = LatestNewsDatasourceImp(apiService, gson)
        newsRepositoryImp = LatestNewsRepositoryImp(newsDatasource)
        newsViewModel = LatestNewsViewModel(newsRepositoryImp)

        newsResponse = LatestNewsResponse()
        enclosure = Enclosure()
        latestNewsItem = LatestNewsItem(
            enclosure, "link", "huid",
            "Description", "title", "cat", "", "content"
        )
        newsList = ArrayList()
        mockkObject(newsDatasourceImp)
        mockkObject(newsRepositoryImp)
        mockkObject(newsViewModel)

        Dispatchers.setMain(Dispatchers.Unconfined)
        Thread.sleep(500)
    }

    var string = ""
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun ` viewmodel test `() {
        // When
        coEvery { newsRepositoryImp.getLatestNews().value?.get(0) } returns latestNewsItem

        // Given
        runBlockingTest {
            withContext(Dispatchers.Unconfined) {
                newsViewModel.getLatestNews()
                newsRepositoryImp.getLatestNews()
                newsViewModel.latestNewsResponse = MutableLiveData()
                newsViewModel.latestNewsResponse!!.value = latestNewsListWithRepo(newsRepositoryImp)
            }
        }

        // Then
        coVerify { newsViewModel.getLatestNews() }

        runBlockingTest { newsRepositoryImp.getLatestNews() }

        Assert.assertEquals(newsList, newsViewModel.latestNewsResponse?.value)
        Assert.assertEquals("Description", newsList[0].description)
        Assert.assertEquals("link", newsList[0].link)
        Assert.assertEquals("content", newsList[0].content)
        Assert.assertEquals("", newsList[0].pubDate)
        Assert.assertEquals(latestNewsItem, newsList[0])

        Assert.assertEquals(enclosure, newsList[0].enclosure)
        Assert.assertEquals(any(), enclosure.url)
        Assert.assertEquals(any(), enclosure.type)
        Assert.assertEquals(any(), enclosure.length)

        Assert.assertNotNull(newsViewModel.status)
    }

    @Test
    fun ` datasource test `() {
        // When
        coEvery { newsDatasourceImp.getLatestNews().value?.get(0) } returns latestNewsItem

        // Given
        runBlockingTest {
            withContext(Dispatchers.Unconfined) {
                newsDatasourceImp.mFetchedLatestNews = MutableLiveData()
                newsDatasourceImp.mFetchedLatestNews.value = latestNewsListWithDatasource(newsDatasourceImp)
            }
        }

        // Then
        coVerify { newsDatasourceImp.mFetchedLatestNews }

        runBlockingTest { newsDatasourceImp.getLatestNews() }

        Assert.assertEquals(newsList, newsDatasourceImp.mFetchedLatestNews.value)
        Assert.assertEquals("Description", newsDatasourceImp.mFetchedLatestNews.value?.get(0)?.description)
        Assert.assertNotNull(newsDatasourceImp.mFetchedLatestNews.value?.get(0)?.enclosure)
        Assert.assertEquals("link", newsDatasourceImp.mFetchedLatestNews.value?.get(0)?.link)

    }

    @Test
    fun ` repo success `() {
        // When
        coEvery { newsRepositoryImp.getLatestNews().value?.get(0) } returns latestNewsItem

        var latestNews = MutableLiveData<List<LatestNewsItem>>()

        // Given
        runBlockingTest {
            withContext(Dispatchers.Unconfined) {
                newsDatasourceImp.getLatestNews()
                latestNews.value = latestNewsListWithRepo(newsRepositoryImp)
            }
        }

        // Then
        coVerify { newsDatasourceImp.getLatestNews() }

        Assert.assertEquals(newsList, latestNews.value)

    }


    private fun latestNewsListWithRepo(newsRepositoryImp: LatestNewsRepositoryImp): List<LatestNewsItem>? {
        return runBlocking {
            withContext(Dispatchers.Unconfined) {
                newsList = newsRepositoryImp.getLatestNews().value!!
                return@withContext newsList
            }
        }
    }

    private fun latestNewsListWithDatasource(datasourceImp: LatestNewsDatasourceImp): List<LatestNewsItem>? {
        return runBlocking {
            withContext(Dispatchers.Unconfined) {
                newsList = datasourceImp.getLatestNews().value!!
                return@withContext newsList
            }
        }
    }
}