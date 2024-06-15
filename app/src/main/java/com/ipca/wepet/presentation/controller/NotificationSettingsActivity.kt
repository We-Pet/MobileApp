package com.ipca.wepet.presentation.controller

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.SettingModel
import com.ipca.wepet.presentation.adapter.SettingsAdapter
import com.ipca.wepet.presentation.fragment.FooterFragment

class NotificationSettingsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_list_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_footer, FooterFragment())
            .commit()

        btnBack = findViewById(R.id.IB_back)
        recyclerView = findViewById(R.id.RV_setting_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val settings = listOf(
            SettingModel(
                1,
                getString(R.string.security),
                getString(R.string.security_alerts_such_as_logging_into_other_devices),
                isEnabled = true
            ),
            SettingModel(
                2,
                getString(R.string.social),
                getString(R.string.someone_followed_her_someone_responded_to_the_post_etc),
                isEnabled = true
            ),
            SettingModel(
                3,
                getString(R.string.market),
                getString(R.string.alerts_about_ads_that_interest_you), isEnabled = true
            ),
            SettingModel(
                4,
                getString(R.string.events),
                getString(R.string.alerts_about_new_events_events_you_have_signed_up_for),
                isEnabled = true
            ),
            SettingModel(
                5,
                getString(R.string.animals),
                getString(R.string.alerts_about_your_animals_such_as_alerts_about_the_next_vaccination),
                isEnabled = true
            ),

            )

        settingsAdapter = SettingsAdapter(settings)
        recyclerView.adapter = settingsAdapter

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
