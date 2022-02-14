package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import java.util.ArrayList

interface WorkoutDAO {
    fun addWorkout(workout: WorkoutModel?): Long
    fun getWorkouts(): ArrayList<WorkoutModel?>?
    fun getWorkout(workoutId: Int): WorkoutModel?
    fun updateWorkouts(workout: WorkoutModel?): Int
    fun deleteWorkouts(workoutId: Int): Int
}