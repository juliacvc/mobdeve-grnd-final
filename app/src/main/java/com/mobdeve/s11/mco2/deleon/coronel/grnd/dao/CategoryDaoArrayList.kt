package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel
import java.util.ArrayList

class CategoryDaoArrayList : CategoryDAO {
    var workoutList = ArrayList<CategoryModel?>()

    init {
        workoutList.add(CategoryModel("CARDIO"))
        workoutList.add(CategoryModel("STRENGTH"))
        workoutList.add(CategoryModel("ENDURANCE"))
        workoutList.add(CategoryModel("YOGA"))
        workoutList.add(CategoryModel("CORE"))
    }

    override fun addWorkout(workout: CategoryModel?): Long {
        workoutList.add(workout!!)

        return 1L
    }

    override fun getWorkouts(): ArrayList<CategoryModel?>? = workoutList

    override fun getWorkouts(workoutId: Int): CategoryModel? {
        TODO("Not yet implemented")
    }

    override fun updateWorkouts(workout: CategoryModel?): Int {
        TODO("Not yet implemented")
    }

    override fun deleteWorkout(workoutId: Int): Int {
        TODO("Not yet implemented")
    }
}