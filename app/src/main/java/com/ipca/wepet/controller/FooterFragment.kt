package com.ipca.wepet.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.ipca.wepet.R

class FooterFragment : Fragment() {

    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnMarketPlace: ImageButton
    private lateinit var btnSocial: ImageButton
    private lateinit var btnProfile: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.footer_layout, container, false)
        initializeElements(view)
        startNewActivities()

        btnSearch.callOnClick()
        return view
    }

    private fun initializeElements(view: View) {
        btnHome = view.findViewById(R.id.BTN_home)
        btnSearch = view.findViewById(R.id.BTN_search)
        btnMarketPlace = view.findViewById(R.id.BTN_marketPlace)
        btnSocial = view.findViewById(R.id.BTN_social)
        btnProfile = view.findViewById(R.id.BTN_profile)
    }

    private fun startNewActivities() {
        // Home button action
        btnHome.setOnClickListener {
            selectButton(btnHome)
        }

        // Search button action
        btnSearch.setOnClickListener {
            selectButton(btnSearch)
        }

        // MarketPlace button action
        btnMarketPlace.setOnClickListener {
            selectButton(btnMarketPlace)
        }

        // Social button action
        btnSocial.setOnClickListener {
            selectButton(btnSocial)
        }

        // Profile button action
        btnProfile.setOnClickListener {
            selectButton(btnProfile)
        }
    }

    private fun selectButton(selectedButton: ImageButton) {
        val buttons = arrayOf(btnHome, btnSearch, btnMarketPlace, btnProfile, btnSocial)
        buttons.forEach { button ->
            button.isSelected = (button == selectedButton)
        }
    }
}

