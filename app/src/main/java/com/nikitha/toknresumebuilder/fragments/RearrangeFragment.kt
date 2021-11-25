package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.RecyclerListAdapter
import com.nikitha.toknresumebuilder.helper.OnStartDragListener
import com.nikitha.toknresumebuilder.helper.SimpleItemTouchHelperCallback


class RearrangeFragment : Fragment(), OnStartDragListener {

    private var mItemTouchHelper: ItemTouchHelper? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return RecyclerView(container!!.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Rearrange sections"

        val adapter = context?.let { RecyclerListAdapter(it, this) }

        val recyclerView = view as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val callback: ItemTouchHelper.Callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(recyclerView)

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        if (viewHolder != null) {
            mItemTouchHelper?.startDrag(viewHolder)
        }
    }


}