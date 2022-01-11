package com.mobdeve.s11.mco2.deleon.coronel.grnd.dao

import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel
import java.util.ArrayList

class PlansDaoArrayList : PlansDAO {
    var plansList = ArrayList<WorkoutPlanModel?>()

    init {
        plansList.add(WorkoutPlanModel(R.drawable.plan1))
        plansList.add(WorkoutPlanModel(R.drawable.plan2))
        plansList.add(WorkoutPlanModel(R.drawable.plan3))
    }

    override fun addPlans(workout: WorkoutPlanModel?): Long {
        plansList.add(workout!!)

        return 1L
    }

    override fun getPlans(): ArrayList<WorkoutPlanModel?>? = plansList

    override fun getPlans(plansId: Int): WorkoutPlanModel? {
        TODO("Not yet implemented")
    }

    override fun updatePlans(workout: WorkoutPlanModel?): Int {
        TODO("Not yet implemented")
    }

    override fun deletePlans(plansId: Int): Int {
        TODO("Not yet implemented")
    }
}