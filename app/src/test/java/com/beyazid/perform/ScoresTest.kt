package com.beyazid.perform

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beyazid.perform.data.datasource.scores.ScoresDatasource
import com.beyazid.perform.data.datasource.scores.ScoresDatasourceImp
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.data.repository.scores.ScoresRepositoryImp
import com.beyazid.perform.data.model.latests_news.Enclosure
import com.beyazid.perform.data.model.latests_news.LatestNewsItem
import com.beyazid.perform.data.model.latests_news.LatestNewsResponse
import com.beyazid.perform.data.model.scores.Competition
import com.beyazid.perform.data.model.scores.Gsmrs
import com.beyazid.perform.data.model.scores.ScoresResponse
import com.beyazid.perform.data.network.ApiService
import com.beyazid.perform.ui.latest_news.LatestNewsFragment
import com.beyazid.perform.ui.scores.ScoresViewModel
import com.google.gson.Gson
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

/**
 *  Created by beyazid on 2019-05-26.
 */
@ExperimentalCoroutinesApi
class ScoresTest {
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
    lateinit var scoresDatasource: ScoresDatasource

    @MockK(relaxed = true)
    lateinit var scoresRepository: ScoresRepository

    @MockK(relaxed = true)
    lateinit var newsFragment: LatestNewsFragment

    @MockK(relaxed = true)
    lateinit var newsResponse: LatestNewsResponse

    lateinit var scoresRepositoryImp: ScoresRepositoryImp
    lateinit var scoresDatasourceImp: ScoresDatasourceImp
    lateinit var scoresVM: ScoresViewModel
//    lateinit var latestNewsItem: LatestNewsItem
    lateinit var scoresResponse: ScoresResponse
    lateinit var enclosure: Enclosure
    lateinit var scores : List<LatestNewsItem>
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        scoresDatasourceImp = spyk(ScoresDatasourceImp(apiService,gson))
        scoresRepositoryImp = spyk(ScoresRepositoryImp(scoresDatasource))
        scoresVM = spyk(ScoresViewModel(scoresRepositoryImp))
        mockScores()
        mockkObject(scoresDatasourceImp)
        mockkObject(scoresRepositoryImp)
        mockkObject(scoresVM)
        scores = ArrayList()
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
        coEvery { scoresRepositoryImp.getScores().value } returns scoresResponse

        // Given
        runBlockingTest {
            withContext(Dispatchers.Unconfined) {
                scoresVM.getScores()
                scoresRepositoryImp.getScores()
            }
        }

        // Then
        coVerify { scoresVM.getScores() }

        runBlockingTest { scoresRepositoryImp.getScores() }

        Assert.assertEquals(scoresResponse, scoresVM.scoresResponse?.value)
        Assert.assertNotNull(scoresResponse.gsmrs)
        Assert.assertNotNull(scoresResponse.gsmrs?.competition)
        Assert.assertNotNull(scoresResponse.gsmrs?.competition?.season)
        Assert.assertNotNull(scoresVM.status)
    }


    private fun mockScores(){
        val competition = mockk<Competition>(relaxed = true)
        val gsmrs = Gsmrs(competition = competition)
        scoresResponse = ScoresResponse(gsmrs)

    }
    @Test
    fun ` datasource test `() {
        // When
        coEvery { scoresDatasourceImp.getScores().value} returns scoresResponse

        // Given
        runBlockingTest {
            withContext(Dispatchers.Unconfined) {
                scoresDatasourceImp.mFetchedScores.value= scoresDatasourceImp.getScores().value
            }
        }

        // Then
        coVerify { scoresDatasourceImp.getScores()}

        Assert.assertEquals(scoresResponse, scoresDatasourceImp.mFetchedScores.value)

    }
//
//    @Test
//    fun ` repo success `() {
//        mockkObject(scoresDatasourceImp)
//        mockkObject(scoresRepositoryImp)
//        mockkObject(scoresVM)
//        // When
//        coEvery { scoresRepositoryImp.getLatestNews().value?.get(0) } returns latestNewsItem
//
//        var latestNews = MutableLiveData<List<LatestNewsItem>>()
//
//        // Given
//        runBlockingTest {
//            withContext(Dispatchers.Unconfined) {
//                scoresDatasourceImp.getLatestNews()
//                latestNews.value = latestNewsListWithRepo(scoresRepositoryImp)
//            }
//        }
//
//        // Then
//        coVerify { scoresDatasourceImp.getLatestNews()}
//
//        Assert.assertEquals(scores, latestNews.value)
//
//    }


//    private fun latestNewsListWithRepo(scoresRepositoryImp: ScoresRepositoryImp): List<LatestNewsItem>? {
//        return runBlocking {
//            withContext(Dispatchers.Unconfined) {
//                scores = scoresRepositoryImp.getLatestNews().value!!
//                return@withContext scores
//            }
//        }
//    }
//    private fun latestNewsListWithDatasource(datasourceImp: ScoresDatasourceImp): List<LatestNewsItem>? {
//        return runBlocking {
//            withContext(Dispatchers.Unconfined) {
//                scores = datasourceImp.getLatestNews().value!!
//                return@withContext scores
//            }
//        }
//    }
}