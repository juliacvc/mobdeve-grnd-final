package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowPopupBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class PlansAdapter(private val workoutList: ArrayList<WorkoutPlanModel?>): RecyclerView.Adapter<PlansAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: WorkoutsRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(item: WorkoutPlanModel, position: Int) {
            itemBinding.workoutImage.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutsRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(workoutList[position]!!, position)
    }
}