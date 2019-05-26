package com.beyazid.perform.utils.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.R
import kotlinx.android.synthetic.main.row_toolbar_header.view.*
import slideToRight


/**
 *  This spinner acts like toolbar
 *  Created by beyazid on 2019-05-22.
 */
class CustomSpinnerAdapter(val context: Context, private val headerList: Array<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = headerList.size

    override fun getItem(position: Int): Any = headerList[position]

    override fun getItemId(i: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: HeaderViewHolder
        val view: View = inflater.inflate(R.layout.row_toolbar_header, parent,false)

//        if (convertView == null) {
        holder = HeaderViewHolder(view)
//            view.tag = holder
            holder.bindItem(headerList[position])
//        } else {
//            holder = convertView.tag as HeaderViewHolder
//            view = convertView
//        }
        return view

    }


    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName = view.row_toolbar_header_tv_header
        fun bindItem(text: String) {
            tvName.slideToRight()
            tvName.text = text
        }
    }

}