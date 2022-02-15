package com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.WorkoutPageFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.WorkoutsRowPopupBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.io.File

class WorkoutAdapter(private val workoutList: ArrayList<WorkoutModel?>): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
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
            val activity = unwrap(v!!.context)
            val workoutPageFragment = WorkoutPageFragment()
            (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.popupWindow, workoutPageFragment).addToBackStack(null).commit()
        }

//        holder.bindItem(workoutList[position]!!, position)
    }

    private fun unwrap(context: Context): Activity? {
        var context: Context? = context
        while (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }
        return context as Activity?
    }
}

