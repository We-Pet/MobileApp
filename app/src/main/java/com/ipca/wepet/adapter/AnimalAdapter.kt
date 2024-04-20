import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.models.AnimalModel

class AnimalAdapter(
    private val animalList: List<AnimalModel>,
    private val listener: OnAnimalClickListener
) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = animalList[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val ivAnimalPhoto: ImageView = itemView.findViewById(R.id.IV_animalPhoto)
        private val tvAnimalName: TextView = itemView.findViewById(R.id.TV_animalName)
        private val ivAnimalSex: ImageView = itemView.findViewById(R.id.IV_animalGender)
        private val tvAnimalRace: TextView = itemView.findViewById(R.id.TV_animalRace)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(animal: AnimalModel) {
            tvAnimalName.text = animal.name
            tvAnimalRace.text = animal.race
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onAnimalClick(position)
            }
        }
    }

    interface OnAnimalClickListener {
        fun onAnimalClick(position: Int)
    }
}
