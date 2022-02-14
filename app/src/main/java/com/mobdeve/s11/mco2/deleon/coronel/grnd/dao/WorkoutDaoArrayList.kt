
package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.util.ArrayList

class WorkoutDaoArrayList: WorkoutDAO {
    var workoutsList = ArrayList<WorkoutModel?>()

    init {
        workoutsList.add(WorkoutModel(R.drawable.plan1))
        workoutsList.add(WorkoutModel(R.drawable.plan2))
        workoutsList.add(WorkoutModel(R.drawable.plan3))
    }

    override fun addWorkout(workout: WorkoutModel?): Long {
        workoutsList.add(workout!!)

        return 1L
    }

    override fun getWorkouts(): ArrayList<WorkoutModel?>? = workoutsList

    override fun getWorkout(workoutId: Int): WorkoutModel? {
        TODO("Not yet implemented")
    }

    override fun updateWorkouts(workout: WorkoutModel?): Int {
        TODO("Not yet implemented")
    }

    override fun deleteWorkouts(workoutId: Int): Int {
        TODO("Not yet implemented")
    }


}