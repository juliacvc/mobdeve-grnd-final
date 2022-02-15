package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.WorkoutActivity
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowPopupBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.io.File

class WorkoutAdapter(private val workoutList: ArrayList<WorkoutModel?>, val context: Context): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    inner class ViewHolder(val itemBinding: WorkoutsRowPopupBinding): RecyclerView.ViewHolder(itemBinding.root) {

        val image = itemBinding.workoutImage
        val title = itemBinding.workoutTitle
        val workout = itemBinding.workout

//        fun bindItem(item: WorkoutModel, position: Int) {
//            itemBinding.workout.setOnClickListener{
//                var gotoWorkoutPage = Intent()
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = WorkoutsRowPopupBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = workoutList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = workoutList[position]
        holder.title.text = currentItem!!.name

        val storageRef = FirebaseStorage.getInstance().reference.child("Workouts/${currentItem!!.image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.image.setImageBitmap(bitmap)
        }

        holder.itemView.setOnClickListener { v ->
//            val activity = unwrap(v!!.context)
//            val workoutPageFragment = WorkoutPageFragment()
//            (activity as FragmentActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentHere, workoutPageFragment).commit()

            var gotoWorkoutPage = Intent(context, WorkoutActivity::class.java)
            var bundle = Bundle()
            bundle.putString("name", currentItem.name)
            bundle.putString("image", currentItem.image)
            bundle.putString("category", currentItem.category)
            bundle.putString("level", currentItem.level)
            bundle.putString("description", currentItem.description)
            bundle.putInt("duration", currentItem.duration)
            bundle.putString("equipment", currentItem.equipment)
            bundle.putString("youtubeLink", currentItem.youtubeLink)
            gotoWorkoutPage.putExtras(bundle)
            context.startActivity(gotoWorkoutPage)
        }

//        holder.bindItem(workoutList[position]!!, position)
    }
}

