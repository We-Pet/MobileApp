package com.ipca.wepet.fragment

import AnimalAdapter
import EventAdapter
import ShelterAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.controller.AnimalActivity
import com.ipca.wepet.controller.EventActivity
import com.ipca.wepet.controller.ShelterActivity
import com.ipca.wepet.models.AnimalModel
import com.ipca.wepet.models.EventModel
import com.ipca.wepet.models.ShelterModel

class TabsFragment(private val recyclerView: RecyclerView) : Fragment(),
    AnimalAdapter.OnAnimalClickListener, ShelterAdapter.OnShelterClickListener,
    EventAdapter.OnEventClickListener {

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

    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalList: List<AnimalModel>

    private lateinit var shelterAdapter: ShelterAdapter
    private lateinit var shelterList: List<ShelterModel>

    private lateinit var eventAdapter: EventAdapter
    private lateinit var eventList: List<EventModel>

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
            setAnimalList()
        }

        ibAnimals.setOnClickListener {
            frameAnimals.callOnClick()
        }

        tvAnimals.setOnClickListener {
            frameAnimals.callOnClick()
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
            setShelterList()
        }

        ibShelters.setOnClickListener {
            frameShelters.callOnClick()
        }

        tvShelters.setOnClickListener {
            frameShelters.callOnClick()
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
            setEventList()
        }

        ibEvents.setOnClickListener {
            frameEvents.callOnClick()
        }

        tvEvents.setOnClickListener {
            frameEvents.callOnClick()
        }
    }

    private fun setAnimalList() {
        animalList = generateAnimalData()
        animalAdapter = AnimalAdapter(animalList, this)
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = animalAdapter
        }
    }

    private fun generateAnimalData(): List<AnimalModel> {
        val animalList = mutableListOf<AnimalModel>()
        for (i in 1..12) {
            val animal = AnimalModel("Id $i", "Animal $i", "Race $i", "Sex $i")
            animalList.add(animal)
        }
        return animalList
    }

    private fun setShelterList() {
        shelterList = generateShelterData()
        shelterAdapter = ShelterAdapter(shelterList, this)
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = shelterAdapter
        }
    }

    private fun generateShelterData(): List<ShelterModel> {
        val shelterList = mutableListOf<ShelterModel>()
        for (i in 1..12) {
            val shelter = ShelterModel("Id $i", "Shelter $i", "Race $i", "Sex $i")
            shelterList.add(shelter)
        }
        return shelterList
    }

    private fun setEventList() {
        eventList = generateEventData()
        eventAdapter = EventAdapter(eventList, this)
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = eventAdapter
        }
    }

    private fun generateEventData(): List<EventModel> {
        val eventList = mutableListOf<EventModel>()
        for (i in 1..12) {
            val event = EventModel("Id $i", "Event $i", "Race $i", "Sex $i")
            eventList.add(event)
        }
        return eventList
    }

    override fun onAnimalClick(position: Int) {
        val animal = animalList[position]
        val intent = Intent(context, AnimalActivity::class.java)
        intent.putExtra("animal", animal.id)
        startActivity(intent)
    }

    override fun onShelterClick(position: Int) {
        val shelter = shelterList[position]
        val intent = Intent(context, ShelterActivity::class.java)
        intent.putExtra("shelter", shelter.id)
        startActivity(intent)
    }

    override fun onEventClick(position: Int) {
        val event = eventList[position]
        val intent = Intent(context, EventActivity::class.java)
        intent.putExtra("event", event.id)
        startActivity(intent)
    }
}

