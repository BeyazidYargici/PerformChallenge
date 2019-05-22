package com.beyazid.perform.ui.standing

import android.os.Bundle
import com.beyazid.perform.R
import com.beyazid.perform.base.BaseFragment
import kotlinx.coroutines.launch

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.standings_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
    }

    private fun getData() = launch{

    }

    private fun initAdapter(){

    }
}