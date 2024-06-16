package com.ipca.wepet.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.SettingModel

class SettingsAdapter(private val settings: List<SettingModel>) :
    RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val setting = settings[position]
        holder.bind(setting)
    }

    override fun getItemCount(): Int {
        return settings.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvSettingName)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvSettingDescription)
        private val switchToggle: Switch = itemView.findViewById(R.id.switchSetting)

        fun bind(setting: SettingModel) {
            nameTextView.text = setting.name
            descriptionTextView.text = setting.description
            switchToggle.isChecked = setting.isEnabled

            switchToggle.setOnCheckedChangeListener { buttonView, isChecked ->
                // Update the setting isEnabled state
                setting.isEnabled = isChecked
            }
        }
    }
}
