package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentCustomDialogBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutPageBinding

class WorkoutPageFragment : Fragment() {
    private lateinit var binding: FragmentWorkoutPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}