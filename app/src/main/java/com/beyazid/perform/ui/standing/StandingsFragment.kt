package com.beyazid.perform.ui.standing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.model.standings.Competition
import com.beyazid.perform.model.standings.RankingItem
import init
import kotlinx.android.synthetic.main.standings_fragment.*
import kotlinx.coroutines.launch
import slideToLeftGroupName
import slideToRightGroupName
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.standings_fragment

    @Inject
    lateinit var vmFactory: StandingsVMFactory

    @Inject
    lateinit var viewModel: StandingsViewModel

    lateinit var adapter: StandingsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(StandingsViewModel::class.java)
        getData()
    }

    private fun getData() = launch {
        viewModel.getStandings().invokeOnCompletion {
            viewModel.standingsResponse?.observe(this@StandingsFragment, Observer {
                if (it == null) return@Observer
                initUI(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI(competition: Competition) {
        fr_standings_tv_league_name.text = competition.name
        fr_standings_tv_league_name.slideToRightGroupName()
        fr_standings_tv_season.text = competition.season?.name
        fr_standings_tv_season.slideToLeftGroupName()
        initAdapter(competition.season?.round?.resultstable?.ranking as List<RankingItem>)
    }

    private fun initAdapter(rankingList: List<RankingItem>) {
        recyclerView = fr_standings_rv_stands
        adapter = StandingsAdapter(activity!!, rankingList)
        recyclerView.init(adapter)
    }
}