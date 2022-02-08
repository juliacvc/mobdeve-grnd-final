package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsListRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav.CustomDialogFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel


class CategoryAdapter(private val workoutList: ArrayList<CategoryModel?>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: WorkoutsListRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

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
                var dialog = CustomDialogFragment()

                //This is the code that doesnt work for some god forsaken reason
//                dialog.show(supportFragmentManager, "customDialog")

                Snackbar.make(itemView, "${item.name}", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutsListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(workoutList[position]!!, position)
    }
}