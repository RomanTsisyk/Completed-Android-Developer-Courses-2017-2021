package com.raywenderlich.android.datadrop.ui.droplist

import androidx.recyclerview.widget.RecyclerView


interface ItemTouchHelperListener {
  fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int)
}