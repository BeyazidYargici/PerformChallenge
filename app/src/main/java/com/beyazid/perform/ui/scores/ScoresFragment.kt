package com.beyazid.perform.ui.scores

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.data.model.scores.Competition
import com.beyazid.perform.data.model.scores.GroupItem
import com.beyazid.perform.data.network.Status
import com.beyazid.perform.utils.createDialog
import gone
import init
import kotlinx.android.synthetic.main.fragment_scores.*
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

/**
 *  This fragment shows matches and scores. These scores updating in every 30 seconds
 */
class ScoresFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_scores

    @Inject
    lateinit var vmFactory: ScoresVMFactory

    @Inject
    lateinit var viewModel: ScoresViewModel

    private lateinit var groupAdapter: GroupAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(ScoresViewModel::class.java)
        progressBall = fr_scores_pb
        getData()
        startTimer()
    }

    // getting data via ViewModel
    private fun getData() {
        viewModel.getScores().invokeOnCompletion {
            viewModel.status?.observe(this, Observer {
                when (viewModel.status?.value?.status) {
                    Status.SUCCESS -> viewModel.scoresResponse?.observe(this, Observer { scores ->
                        if (scores == null) return@Observer
                        scores.gsmrs?.competition?.let { competition -> initUI(competition) }
                    })
                    else -> createDialog(activity!!, it.code.toString(), it.message!!)
                }
                progressBall.gone()
            })
        }
    }

    private fun initUI(competition: Competition) {
        fr_scores_tv_competition_name.text = competition.name
        fr_scores_tv_date.text = competition.lastUpdated
        initAdapter(competition.season?.round?.group as List<GroupItem>)

    }

    // initialize the adapter for showing scores view
    private fun initAdapter(groups: List<GroupItem>) {
        recyclerView = fr_scores_rv
        groupAdapter = GroupAdapter(activity!!, groups)
        recyclerView.init(groupAdapter)
    }

    lateinit var timer: Timer

    // This timer triggers to fetch data from api in every 30 seconds
    private fun startTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runBlocking { getData() }
            }
        }, 0, 30000)
    }

    override fun onPause() {
        super.onPause()
        // cancel timer for avoiding memory leak and unnecessary data usage
        timer.cancel()
    }
}
