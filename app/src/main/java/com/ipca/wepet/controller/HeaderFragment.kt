package com.ipca.wepet.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ipca.wepet.R

class HeaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.header_layout, container, false)
        //initializeElements(view)
        //startNewActivities()
        return view
    }

    private fun initializeElements(view: View) {
    }

    private fun startNewActivities() {

    }
}

