package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel
import java.util.ArrayList

interface PlansDAO {
    fun addPlans(workout: WorkoutPlanModel?): Long
    fun getPlans(): ArrayList<WorkoutPlanModel?>?
    fun getPlans(plansId: Int): WorkoutPlanModel?
    fun updatePlans(workout: WorkoutPlanModel?): Int
    fun deletePlans(plansId: Int): Int
}