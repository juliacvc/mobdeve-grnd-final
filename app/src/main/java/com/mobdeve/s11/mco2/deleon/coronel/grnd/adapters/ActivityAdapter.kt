package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.NewsRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutListRowBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.io.File

class ActivityAdapter( private val workoutsList: ArrayList<WorkoutModel?>): RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: WorkoutListRowBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val image = itemBinding.workoutImage
        val name = itemBinding.workoutName
        val category = itemBinding.workoutCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutListRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = workoutsList[position]

        val storageRef = FirebaseStorage.getInstance().reference.child("Workouts/${currentItem!!.image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.image.setImageBitmap(bitmap)
            holder.name.text = currentItem.name
            holder.category.text = currentItem.category
        }
    }
}