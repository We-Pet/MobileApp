package com.ipca.wepet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.ipca.wepet.R
import com.ipca.wepet.controller.ProfilActivity
import com.ipca.wepet.controller.ShelterActivity

class HeaderFragment : Fragment() {
    private lateinit var ibHamburger: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.header_layout, container, false)

        initializeElements(view)
        setListeners()

        return view
    }

    private fun initializeElements(view: View) {
        ibHamburger = view.findViewById(R.id.IB_hamburger)
    }

    private fun setListeners() {
        // Hamburger button action
        ibHamburger.setOnClickListener {
            showPopupMenu(it)
        }
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.hamburguer_menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.IT_my_animals -> {
                    // Handle menu my animals click
                    true
                }

                R.id.IT_edit_profil -> {
                    // Handle menu edit profil click
                    val intent = Intent(context, ProfilActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.IT_desative_account -> {
                    // Handle menu desative account click
                    true
                }

                else -> false
            }
        }
        popup.show()
    }
}
