package com.beyazid.perform.ui.scores

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.model.scores.Competition
import com.beyazid.perform.model.scores.GroupItem
import init
import kotlinx.android.synthetic.main.scores_fragment.*
import javax.inject.Inject

class ScoresFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.scores_fragment

    @Inject
    lateinit var vmFactory: ScoresVMFactory

    @Inject
    lateinit var viewModel: ScoresViewModel

    lateinit var groupAdapter: GroupAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(ScoresViewModel::class.java)
        getData()
    }

    private fun getData() {
        viewModel.getScores().invokeOnCompletion {
            viewModel.scoresResponse?.observe(this@ScoresFragment, Observer {
                if (it == null) return@Observer
                it.gsmrs?.competition?.let { competition -> initUI(competition) }
            })
        }
    }

    private fun initUI(competition: Competition) {
        fr_scores_tv_competition_name.text = competition.name
        fr_scores_tv_date.text = competition.lastUpdated
        initAdapter(competition.season?.round?.group as List<GroupItem>)

    }

    private fun initAdapter(groups: List<GroupItem>) {
        recyclerView = fr_scores_rv
        groupAdapter = GroupAdapter(activity!!, groups)
        recyclerView.init(groupAdapter)
    }
}
