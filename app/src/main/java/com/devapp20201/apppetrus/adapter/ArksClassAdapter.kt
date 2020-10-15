package com.devapp20201.apppetrus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devapp20201.apppetrus.R
import com.devapp20201.apppetrus.model.ArksClass
import kotlinx.android.synthetic.main.item_class.view.*

class ArksClassAdapter(private val context: Context, private val classes: MutableList<ArksClass>, private val callback: (ArksClass)-> Unit) :
    RecyclerView.Adapter<ArksClassAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_class
            , parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val arksclass = classes[vh.adapterPosition]
            callback(arksclass)
        }
        return vh
    }

    override fun getItemCount(): Int = classes.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val aclass = classes[position]
        holder.txtName.text = aclass.name

    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.textViewClassNome


    }



}