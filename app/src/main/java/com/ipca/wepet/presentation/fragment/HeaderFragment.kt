package com.ipca.wepet.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.ipca.wepet.R
import com.ipca.wepet.presentation.controller.AboutUsActivity
import com.ipca.wepet.presentation.controller.ContactUsActivity
import com.ipca.wepet.presentation.controller.LoginActivity
import com.ipca.wepet.presentation.controller.ProfileActivity
import com.ipca.wepet.util.EmailUtils
import com.ipca.wepet.util.ToastHandler

class HeaderFragment : Fragment() {
    private lateinit var ibHamburger: ImageButton
    private lateinit var sharedPreferences: SharedPreferences

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
        sharedPreferences = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
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
                    // Handle menu edit profile click
                    val intent = Intent(context, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.IT_desative_account -> {
                    // Handle menu deactivate account click
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

                R.id.IT_log_out -> {
                    // Handle menu log out click
                    clearAuthPreferences()

                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                    context?.let { ToastHandler.showToast(it, R.string.logged_out) }

                    true
                }

                else -> false
            }
        }
        popup.show()
    }

    private fun clearAuthPreferences() {
        val sharedPreferences = requireActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun showBottomDialogDeleteAccount() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_delete_account)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)
        val emailEditText: EditText = dialog.findViewById(R.id.ET_email)
        val passwordEditText: EditText = dialog.findViewById(R.id.ET_password)

        btnContinue.setOnClickListener {
            if (checkEmailAndPasswordFields(emailEditText, passwordEditText)) {
                showConfirmationDialog()
                dialog.dismiss()
            }
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun checkEmailAndPasswordFields(
        emailEditText: EditText,
        passwordEditText: EditText
    ): Boolean {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val sharedPreferenceEMAIL = sharedPreferences.getString("EMAIL", "")
        val sharedPreferencesPASSWORD = sharedPreferences.getString("PASSWORD", "")

        if (email.isBlank()) {
            ToastHandler.showToast(requireContext(), R.string.error_empty_email)
            return false
        } else if (!EmailUtils.isEmailValid(email) || email != sharedPreferenceEMAIL) {
            ToastHandler.showToast(requireContext(), R.string.error_invalid_email)
            return false
        } else if (password.isBlank()) {
            ToastHandler.showToast(requireContext(), R.string.error_empty_password)
            return false
        } else if (password != sharedPreferencesPASSWORD) {
            ToastHandler.showToast(requireContext(), R.string.error_invalid_password)
            return false
        }
        return true
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
        deleteAccountFromFirebase()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun deleteAccountFromFirebase() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()?.addOnSuccessListener {
            Log.d("DELETE ACCOUNT", "User account deleted.")
        }?.addOnFailureListener { e ->
            Log.e("DELETE ACCOUNT", "Error deleting user account", e)
        }
    }

}
