import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.models.ShelterModel

class ShelterAdapter(
    private val shelterList: List<ShelterModel>,
    private val listener: OnShelterClickListener
) :
    RecyclerView.Adapter<ShelterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shelter_item_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shelter = shelterList[position]
        holder.bind(shelter)
    }

    override fun getItemCount(): Int {
        return shelterList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val ivShelterPhoto: ImageView = itemView.findViewById(R.id.IV_shelter_photo)
        private val tvShelterName: TextView = itemView.findViewById(R.id.TV_shelter_name)
        private val ivSheltergender: ImageView = itemView.findViewById(R.id.IV_shelter_gender)
        private val tvShelterRace: TextView = itemView.findViewById(R.id.TV_shelter_race)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(shelter: ShelterModel) {
            tvShelterName.text = shelter.name
            tvShelterRace.text = shelter.race
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onShelterClick(position)
            }
        }
    }

    interface OnShelterClickListener {
        fun onShelterClick(position: Int)
    }
}
