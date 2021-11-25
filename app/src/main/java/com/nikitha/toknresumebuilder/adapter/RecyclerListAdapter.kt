package com.nikitha.toknresumebuilder.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.helper.ItemTouchHelperAdapter
import com.nikitha.toknresumebuilder.helper.ItemTouchHelperViewHolder
import com.nikitha.toknresumebuilder.helper.OnStartDragListener
import java.util.*

class RecyclerListAdapter(val context: Context, dragStartListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    private val mItems: ArrayList<String> = ArrayList<String>()
    private val mDrawable: ArrayList<String> = ArrayList<String>()
    private var mDragStartListener: OnStartDragListener? = null

    val drawable_array = arrayOf(R.drawable.person, R.drawable.education, R.drawable.profession_detail,
    R.drawable.ic_twotone_shield_24, R.drawable.ic_baseline_flag_24, R.drawable.ic_baseline_sd_card_24, R.drawable.ic_baseline_add_circle_outline_24
    )

    init{
        mDragStartListener = dragStartListener
        mItems.addAll(context.resources.getStringArray(R.array.re_arrange_sections))
        mDrawable.addAll(context.resources.getStringArray(R.array.re_arrange_drawable))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_rearrange_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text).text = mItems[position]
        holder.itemView.findViewById<TextView>(R.id.text).setCompoundDrawablesWithIntrinsicBounds(drawable_array[position], 0, 0,0)

        // Start a drag whenever the handle view it touched
        holder.itemView.findViewById<ImageView>(R.id.handle).setOnTouchListener(OnTouchListener { v, event ->
            if (MotionEventCompat.getActionMasked(event) === MotionEvent.ACTION_DOWN) {
                mDragStartListener!!.onStartDrag(holder)
            }
            false
        })
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        ItemTouchHelperViewHolder {
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }


    }
}