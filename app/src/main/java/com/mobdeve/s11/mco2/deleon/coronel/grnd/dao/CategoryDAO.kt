package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel
import java.util.ArrayList

interface CategoryDAO {
    fun addWorkout(workout: CategoryModel?): Long
    fun getWorkouts(): ArrayList<CategoryModel?>?
    fun getWorkouts(workoutId: Int): CategoryModel?
    fun updateWorkouts(workout: CategoryModel?): Int
    fun deleteWorkout(workoutId: Int): Int
}