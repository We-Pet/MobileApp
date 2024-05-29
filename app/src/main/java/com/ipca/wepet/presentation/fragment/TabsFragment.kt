package com.ipca.wepet.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ipca.wepet.presentation.fragment.animal.AnimalViewModel
import com.ipca.wepet.presentation.fragment.event.EventViewModel
import com.ipca.wepet.presentation.fragment.shelter.ShelterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment : Fragment() {

    private val animalViewModel: AnimalViewModel by viewModels()
    private val shelterViewModel: ShelterViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ListScreen(animalViewModel, shelterViewModel, eventViewModel)
            }
        }
    }
}

