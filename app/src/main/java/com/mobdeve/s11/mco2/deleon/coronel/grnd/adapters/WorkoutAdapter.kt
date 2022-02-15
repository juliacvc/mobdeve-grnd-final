package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowPopupBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.io.File

class WorkoutAdapter(private val workoutList: ArrayList<WorkoutModel?>): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: WorkoutsRowPopupBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val image = itemBinding.workoutImage
        val title = itemBinding.workoutTitle
//        fun bindItem(item: WorkoutModel, position: Int) {
//            itemBinding.workoutImage.setImageResource(item.image)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutsRowPopupBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindItem(workoutList[position]!!, position)

        val currentItem = workoutList[position]
        holder.title.text = currentItem!!.name

        val storageRef = FirebaseStorage.getInstance().reference.child("Workouts/${currentItem!!.image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.image.setImageBitmap(bitmap)
        }
    }
}