package com.beyazid.perform.ui.standing

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.R
import com.beyazid.perform.model.standings.RankingItem
import kotlinx.android.synthetic.main.row_standings.view.*
import rankColor
import slideToLeftGroupName
import slideToRightGroupName
import textColor

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsAdapter(val context: Context, val rankingList: List<RankingItem>) :
    RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {
    var zone: String? = null
    var isZoneEnabled = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_standings, parent, false)
        return StandingsViewHolder(v)
    }

    override fun getItemCount(): Int = rankingList.size

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) = holder.bindItem(position)


    inner class StandingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val container = view.row_standings_ll_container
        private val tvRank = view.row_standings_tv_rank
        private val tvClubName = view.row_standings_tv_club_name
        private val tvTotalGame = view.row_standings_tv_total_game
        private val tvWin = view.row_standings_tv_win
        private val tvDraw = view.row_standings_tv_draw
        private val tvLose = view.row_standings_tv_lose
        private val tvAverage = view.row_standings_tv_average
        private val tvPoints = view.row_standings_tv_points


        @SuppressLint("SetTextI18n")
        fun bindItem(pos: Int) {
            val rankingItem = rankingList[pos]
            tvRank.text = rankingItem.rank
            tvClubName.text = rankingItem.clubName
            tvClubName.isSelected = true
            tvTotalGame.text = rankingItem.matchesTotal
            tvWin.text = rankingItem.matchesWon
            tvDraw.text = rankingItem.matchesDraw
            tvLose.text = rankingItem.matchesLost
            tvAverage.text =
                ((rankingItem.goalsPro?.toInt()?.minus(rankingItem.goalsAgainst?.toInt()!!)).toString())
            tvPoints.text = rankingItem.points
            if (pos % 2 == 0) evenItem() else oddItem()
            isInZone(rankingItem.zoneStart, rankingItem.zoneEnd)
        }

        //Black Alpha
        private fun oddItem() {
            container.background = ContextCompat.getDrawable(context, R.drawable.row_odd_background)
            tvTextColors(R.color.black_alpha_text)
            tvClubName.slideToRightGroupName()
        }

        private fun evenItem() {
            container.background = ContextCompat.getDrawable(context, R.drawable.row_even_background)
            tvTextColors(R.color.white_alpha_text)
            tvClubName.slideToLeftGroupName()
        }

        private fun tvTextColors(textColorRes: Int) {
            tvClubName.textColor(textColorRes)
            tvRank.textColor(textColorRes)
            tvTotalGame.textColor(textColorRes)
            tvWin.textColor(textColorRes)
            tvDraw.textColor(textColorRes)
            tvLose.textColor(textColorRes)
            tvAverage.textColor(textColorRes)
            tvPoints.textColor(textColorRes)
        }

        /**
         * Set zone ui if the team is in the any zone
         */
        private fun isInZone(start: String?, end: String?) {
            if (!isZoneEnabled) {
                if (start != null) {
                    isZoneEnabled = true
                    zone = start
                } else zone = null
            } else {
                if (end != null && end == zone) isZoneEnabled = false
                if (end != null && start != null && end == start) {
                    isZoneEnabled = false
                    zone = start
                }
            }
            zone?.let {
                tvRank.rankColor(zone!!)
                tvRank.textColor(R.color.black_alpha_text)
            }
        }
    }
}

