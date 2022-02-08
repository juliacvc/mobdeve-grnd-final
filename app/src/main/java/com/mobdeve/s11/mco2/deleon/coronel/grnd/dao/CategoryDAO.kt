package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel
import java.util.ArrayList

interface CategoryDAO {
    fun addWorkout(category: CategoryModel?): Long
    fun getWorkouts(): ArrayList<CategoryModel?>?
    fun getWorkouts(categoryId: Int): CategoryModel?
    fun updateWorkouts(category: CategoryModel?): Int
    fun deleteWorkout(categoryId: Int): Int
}