package com.ipca.wepet.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.ipca.wepet.R
import com.ipca.wepet.controller.AboutUsActivity
import com.ipca.wepet.controller.ContactUsActivity
import com.ipca.wepet.controller.LoginActivity
import com.ipca.wepet.controller.ProfilActivity

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
                    showBottomDialogDeleteAccount()
                    true
                }

                R.id.IT_about_us -> {
                    // Handle menu about us click
                    val intent = Intent(context, AboutUsActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.IT_support -> {
                    // Handle menu support click
                    val intent = Intent(context, ContactUsActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
        popup.show()
    }

    private fun showBottomDialogDeleteAccount() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_delete_account)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)

        btnContinue.setOnClickListener {
            showConfirmationDialog()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.confirm_action))
        builder.setMessage(getString(R.string.confirm_delete_account))

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            deleteAccount()
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ ->
            // Do nothing or handle cancel
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun deleteAccount() {
        // TODO: Implement account deletion logic
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }


}
