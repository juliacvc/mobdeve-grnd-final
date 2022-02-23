package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.PlansListRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class WorkoutPlansAdapter(var context: Context,
                      private val plansList: ArrayList<WorkoutPlanModel?>,
                      private val onItemClicked: (WorkoutPlanModel)->Unit
): RecyclerView.Adapter<WorkoutPlansAdapter.ViewHolder>() {
    inner class ViewHolder(itemBinding: PlansListRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val name = itemBinding.planName
        val description = itemBinding.planDescription
        val item = itemBinding.workoutPlan
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = PlansListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = plansList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = plansList[position]
        holder.name.text = currentItem!!.name
        holder.description.text = currentItem.description

        if (position % 2 == 0) {
            holder.item.setBackgroundColor(Color.parseColor("#072227"))
            holder.name.setTextColor(Color.parseColor("#FFFFFF"))
            holder.description.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else {
            holder.item.setBackgroundColor(Color.parseColor("#FFFFFF"))
            holder.name.setTextColor(Color.parseColor("#000000"))
            holder.description.setTextColor(Color.parseColor("#000000"))
        }

        holder.item.setOnClickListener{
            onItemClicked(plansList[position]!!)
        }
    }
}