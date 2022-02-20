package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.CategoryListRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel

class CategoryAdapter(var context: Context,
                      private val workoutList: ArrayList<CategoryModel?>,
                      private val onItemClicked: (CategoryModel)->Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: CategoryListRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(item: CategoryModel, position: Int) {
            itemBinding.workoutItem.text = item.name

            if (position % 2 == 0) {
                itemBinding.workoutItem.setBackgroundColor(Color.parseColor("#072227"))
                itemBinding.workoutItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else {
                itemBinding.workoutItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
                itemBinding.workoutItem.setTextColor(Color.parseColor("#000000"))
            }

            itemBinding.workoutItem.setOnClickListener{
                onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CategoryListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(workoutList[position]!!, position)
    }
}