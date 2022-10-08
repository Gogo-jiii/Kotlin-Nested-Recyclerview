package com.example.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_child.view.*

class AdapterChild(
    private val context: Context,
    private val arrayListParent: ArrayList<ModelParent>,
    private val arrayListChild: ArrayList<ModelChild>
) : RecyclerView.Adapter<AdapterChild.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_child, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelChild = arrayListChild[position]
        holder.itemView.textViewChild.text = modelChild.child
    }

    override fun getItemCount(): Int {
        return arrayListChild.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewChild: TextView

        init {
            textViewChild = itemView.findViewById(R.id.textViewChild)

            itemView.setOnClickListener {
                val modelParent = arrayListParent[AdapterParent.parentPosition]
                val arrayListChild = modelParent.arrayListChild
                Toast.makeText(
                    context, """
     Parent: ${modelParent.parent}
     Child: ${arrayListChild[adapterPosition]}
     """.trimIndent(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}