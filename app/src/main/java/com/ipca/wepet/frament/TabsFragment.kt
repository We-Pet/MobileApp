package com.ipca.wepet.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ipca.wepet.R

class TabsFragment : Fragment() {

    private lateinit var frameAnimals: FrameLayout
    private lateinit var ibAnimals: ImageButton
    private lateinit var tvAnimals: TextView
    private lateinit var vAnimals: View

    private lateinit var frameShelters: FrameLayout
    private lateinit var ibShelters: ImageButton
    private lateinit var tvShelters: TextView
    private lateinit var vShelters: View

    private lateinit var frameEvents: FrameLayout
    private lateinit var ibEvents: ImageButton
    private lateinit var tvEvents: TextView
    private lateinit var vEvents: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tabs_layout, container, false)
        initializeElements(view)
        setupButtonListeners()

        // Default
        frameAnimals.callOnClick()
        return view
    }

    private fun initializeElements(view: View) {
        frameAnimals = view.findViewById(R.id.layout_animals)
        ibAnimals = view.findViewById(R.id.IB_animals)
        tvAnimals = view.findViewById(R.id.TV_animal)
        vAnimals = view.findViewById(R.id.V_animal)

        frameShelters = view.findViewById(R.id.layout_shelters)
        ibShelters = view.findViewById(R.id.IB_shelters)
        tvShelters = view.findViewById(R.id.TV_shelter)
        vShelters = view.findViewById(R.id.V_shelter)

        frameEvents = view.findViewById(R.id.layout_events)
        ibEvents = view.findViewById(R.id.IB_events)
        tvEvents = view.findViewById(R.id.TV_events)
        vEvents = view.findViewById(R.id.V_event)
    }

    private fun setButtonProperties(
        button: ImageButton,
        text: TextView,
        line: View,
        iconResource: Int,
        textColor: Int,
        lineVisibility: Int
    ) {
        button.setImageResource(iconResource)
        text.setTextColor(textColor)
        line.visibility = lineVisibility
    }

    private fun setupButtonListeners() {
        // Animal button action
        frameAnimals.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        ibAnimals.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        tvAnimals.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        // Shelter button action
        frameShelters.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        ibShelters.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        tvShelters.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        // Event button action
        frameEvents.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }

        ibEvents.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }

        tvEvents.setOnClickListener {
            setButtonProperties(
                ibAnimals,
                tvAnimals,
                vAnimals,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibShelters,
                tvShelters,
                vShelters,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                ibEvents,
                tvEvents,
                vEvents,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }
    }
}
