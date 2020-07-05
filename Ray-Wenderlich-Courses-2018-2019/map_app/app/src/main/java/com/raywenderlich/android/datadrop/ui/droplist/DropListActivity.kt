package com.raywenderlich.android.datadrop.ui.droplist

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.datadrop.R
import com.raywenderlich.android.datadrop.model.Drop
import com.raywenderlich.android.datadrop.viewmodel.DropsViewModel
import kotlinx.android.synthetic.main.activity_list.*

class DropListActivity : AppCompatActivity(), DropListAdapter.DropListAdapterListener {

    private lateinit var dropsViewModel: DropsViewModel
    private val adapter = DropListAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        title = getString(R.string.all_drops)

        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))

        itemTouchHelper.attachToRecyclerView(listRecyclerView)

        dropsViewModel = ViewModelProviders.of(this).get(DropsViewModel::class.java)

        dropsViewModel.getDrops().observe(this, Observer<List<Drop>> {
            adapter.updateDrops(it ?: emptyList())
            checkForEmptyState()
        })
    }


    override fun deleteDropAtPosition(drop: Drop) {

        dropsViewModel.clearDrop(drop)
    }

    private fun checkForEmptyState() {
        emptyState.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, DropListActivity::class.java)
    }
}
