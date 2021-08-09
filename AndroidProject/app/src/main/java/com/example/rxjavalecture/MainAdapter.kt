package com.example.rxjavalecture

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ExampleItem.values()

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val btnMoveToExampleDestination: Button = v.findViewById(R.id.btn_move_to_example_destination)

        fun onBind(exampleItem: ExampleItem) {
            btnMoveToExampleDestination.text = context.getString(exampleItem.exampleTitleRes)
            btnMoveToExampleDestination.setOnClickListener {
                context.startActivity(Intent(context, exampleItem.destinationClass))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_example, parent, false) as View
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.onBind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}