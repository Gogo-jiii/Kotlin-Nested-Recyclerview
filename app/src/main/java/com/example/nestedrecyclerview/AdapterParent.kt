package com.example.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import kotlinx.android.synthetic.main.row_parent.view.*

class AdapterParent(context: MainActivity, arrayListParent:
ArrayList<ModelParent>) :
    RecyclerView.Adapter<AdapterParent.ViewHolder>() {

    private val context: Context
    private val arrayListParent: ArrayList<ModelParent>
    private val viewPool = RecycledViewPool()

    init {
        this.context = context
        this.arrayListParent = arrayListParent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_parent,
            parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelParent = arrayListParent[position]
        val arrayListChild = modelParent.arrayListChild
        holder.itemView.textViewParent.text = modelParent.parent

        //Child Recyclerview
        val linearLayoutManager = LinearLayoutManager(
            holder.itemView.recyclerviewChild.context,
            LinearLayoutManager.VERTICAL, false
        )
        linearLayoutManager.initialPrefetchItemCount = arrayListChild.size
        val adapterChild = AdapterChild(context, arrayListParent, arrayListChild)
        holder.itemView.recyclerviewChild.layoutManager = linearLayoutManager
        holder.itemView.recyclerviewChild.adapter = adapterChild
        holder.itemView.recyclerviewChild.setRecycledViewPool(viewPool)
        if (modelParent.isChildVisible) {
            holder.itemView.recyclerviewChild.visibility = View.VISIBLE
        } else {
            holder.itemView.recyclerviewChild.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return arrayListParent.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerViewChild: RecyclerView
        private val textView: TextView

        init {
            textView = itemView.findViewById(R.id.textViewParent)
            recyclerViewChild = itemView.findViewById(R.id.recyclerviewChild)
            itemView.setOnClickListener {
                parentPosition = adapterPosition
                Toast.makeText(
                    context, arrayListParent[adapterPosition].parent,
                    Toast.LENGTH_SHORT
                ).show()
                val modelParent = arrayListParent[adapterPosition]
                modelParent.isChildVisible = !modelParent.isChildVisible
                notifyItemChanged(adapterPosition)
            }
        }
    }

    companion object {
        var parentPosition = -1
    }
}