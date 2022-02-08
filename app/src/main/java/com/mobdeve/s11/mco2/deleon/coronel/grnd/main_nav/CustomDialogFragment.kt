package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityLoginBinding.inflate

class CustomDialogFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.custom_popup_dialogue, container, false)

        return rootView


    }


}