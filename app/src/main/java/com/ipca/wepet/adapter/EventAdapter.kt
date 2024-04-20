import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.models.EventModel

class EventAdapter(
    private val eventList: List<EventModel>,
    private val listener: OnEventClickListener
) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val ivEventPhoto: ImageView = itemView.findViewById(R.id.IV_event_photo)
        private val tvEventName: TextView = itemView.findViewById(R.id.TV_event_name)
        private val ivEventGender: ImageView = itemView.findViewById(R.id.IV_event_gender)
        private val tvEventRace: TextView = itemView.findViewById(R.id.TV_event_race)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(event: EventModel) {
            tvEventName.text = event.name
            tvEventRace.text = event.race
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onEventClick(position)
            }
        }
    }

    interface OnEventClickListener {
        fun onEventClick(position: Int)
    }
}
