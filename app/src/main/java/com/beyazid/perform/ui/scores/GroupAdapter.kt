package com.beyazid.perform.ui.scores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.R
import com.beyazid.perform.data.model.scores.GroupItem
import com.beyazid.perform.data.model.scores.MatchItem
import init
import kotlinx.android.synthetic.main.row_group.view.*
import slideToLeftGroupName
import slideToRightGroupName

/**
 *  Created by beyazid on 2019-05-22.
 */
class GroupAdapter(val context: Context, val groups: List<GroupItem>) :
    RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    lateinit var matchAdapter: MatchAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_group, parent, false)
        return GroupViewHolder(v)
    }

    override fun getItemCount(): Int = groups.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) = holder.bindItem(position)

    inner class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvGroupName = view.row_group_tv_name
        private val rvMatches = view.row_group_rv_matches
        fun bindItem(pos: Int) {
            val group = groups[pos]
            tvGroupName.text = group.name
            if (pos%2==0) tvGroupName.slideToLeftGroupName() else tvGroupName.slideToRightGroupName()
            setAdapter(group.match as List<MatchItem>)
        }
        private fun setAdapter(matches : List<MatchItem>){
            matchAdapter = MatchAdapter(context,matches)
            rvMatches.init(matchAdapter)
        }
    }
}
