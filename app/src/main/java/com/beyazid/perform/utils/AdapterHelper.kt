//package com.beyazid.perform.utils
//
//import androidx.recyclerview.widget.DiffUtil
//import com.beyazid.perform.data.model.response.list.DataItem
//
///**
// *  Created by beyazid on 2019-05-17.
// */
//
//var DIFF_CALLBACK: DiffUtil.ItemCallback<DataItem> = object : DiffUtil.ItemCallback<DataItem>() {
//    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem.id=== newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        return oldItem == newItem
//    }
//}
