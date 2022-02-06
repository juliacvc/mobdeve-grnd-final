package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

}