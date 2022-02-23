package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel
import java.io.File

class OngoingPlansAdapter(private val workoutList: ArrayList<WorkoutPlanModel?>): RecyclerView.Adapter<OngoingPlansAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: WorkoutsRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val image = itemBinding.workoutImage

//        fun bindItem(item: WorkoutPlanModel, position: Int) {
//            itemBinding.workoutImage.setImageResource(item.image)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutsRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(workoutList[position]!!, position)

        val currentItem = workoutList[position]

        val storageRef = FirebaseStorage.getInstance().reference.child("Plans/${currentItem!!.image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.image.setImageBitmap(bitmap)
        }
    }
}