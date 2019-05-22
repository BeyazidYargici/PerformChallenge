package com.beyazid.perform.ui.scores

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.R
import com.beyazid.perform.model.scores.GroupItem
import com.beyazid.perform.model.scores.MatchItem
import kotlinx.android.synthetic.main.row_scores.view.*

/**
 *  Created by beyazid on 2019-05-21.
 */
class MatchAdapter(val context: Context, val matches: List<MatchItem>) :
    RecyclerView.Adapter<MatchAdapter.ScoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_scores, parent, false)
        return ScoresViewHolder(v)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) = holder.bindItem(position)


    inner class ScoresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val container = view.fr_scores_ll_container
        private val tvHome = view.row_scores_tv_home
        private val tvAway = view.row_scores_tv_away
        private val tvScore = view.row_scores_tv_live_score

        @SuppressLint("SetTextI18n")
        fun bindItem(pos: Int) {
            val match = matches[pos]
            tvHome.text = match.teamAName
            tvAway.text = match.teamBName
            tvScore.text = "${match.fsA} - ${match.fsB}"
            if (pos%2==0) evenItem() else oddItem()
        }
        private fun oddItem() {
            container.background = ContextCompat.getDrawable(context,R.drawable.row_score_teams_background)
            tvHome.setTextColor(ContextCompat.getColor(context,R.color.black_alpha_text))
            tvAway.setTextColor(ContextCompat.getColor(context,R.color.black_alpha_text))
            tvScore.setTextColor(ContextCompat.getColor(context,R.color.white_alpha_text))
        }

        private fun evenItem() {
            container.background = ContextCompat.getDrawable(context,R.drawable.row_score_teams_reverse_background)
            tvHome.setTextColor(ContextCompat.getColor(context,R.color.white_alpha_text))
            tvAway.setTextColor(ContextCompat.getColor(context,R.color.white_alpha_text))
            tvScore.setTextColor(ContextCompat.getColor(context,R.color.black_alpha_text))
        }

    }

}