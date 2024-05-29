package com.ipca.wepet.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ipca.wepet.R

class SearchFragment : Fragment() {

    private lateinit var btnHamburger: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var tvSearch: TextView
    private lateinit var btnFilters: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_layout, container, false)
        initializeElements(view)
        return view
    }

    private fun initializeElements(view: View) {
        btnHamburger = view.findViewById(R.id.BTN_hamburger)
        tvSearch = view.findViewById(R.id.TV_search)
        btnSearch = view.findViewById(R.id.BTN_search)
        btnFilters = view.findViewById(R.id.BTN_filters)
    }
}
