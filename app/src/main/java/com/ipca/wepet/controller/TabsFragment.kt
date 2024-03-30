import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ipca.wepet.R

class TabsFragment : Fragment() {

    private lateinit var frameAnimals: FrameLayout
    private lateinit var btnAnimals: ImageButton
    private lateinit var txtAnimals: TextView
    private lateinit var animalsLine: View

    private lateinit var frameShelters: FrameLayout
    private lateinit var btnShelters: ImageButton
    private lateinit var txtShelters: TextView
    private lateinit var sheltersLine: View

    private lateinit var frameEvents: FrameLayout
    private lateinit var btnEvents: ImageButton
    private lateinit var txtEvents: TextView
    private lateinit var eventsLine: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tabs_layout, container, false)
        initializeElements(view)
        setupButtonListeners()
        return view
    }

    private fun initializeElements(view: View) {
        frameAnimals = view.findViewById(R.id.layout_animals)
        btnAnimals = view.findViewById(R.id.BTN_animals)
        txtAnimals = view.findViewById(R.id.text_animal)
        animalsLine = view.findViewById(R.id.line_animal)

        frameShelters = view.findViewById(R.id.layout_shelters)
        btnShelters = view.findViewById(R.id.BTN_shelters)
        txtShelters = view.findViewById(R.id.text_shelter)
        sheltersLine = view.findViewById(R.id.line_shelter)

        frameEvents = view.findViewById(R.id.layout_events)
        btnEvents = view.findViewById(R.id.BTN_events)
        txtEvents = view.findViewById(R.id.text_events)
        eventsLine = view.findViewById(R.id.line_event)
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
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        btnAnimals.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        txtAnimals.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animals_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        // Shelter button action
        frameShelters.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        btnShelters.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        txtShelters.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
        }

        // Event button action
        frameEvents.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }

        btnEvents.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }

        txtEvents.setOnClickListener {
            setButtonProperties(
                btnAnimals,
                txtAnimals,
                animalsLine,
                R.drawable.tab_animal_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnShelters,
                txtShelters,
                sheltersLine,
                R.drawable.tab_shelter_icon_black,
                ContextCompat.getColor(requireContext(), R.color.black),
                View.INVISIBLE
            )
            setButtonProperties(
                btnEvents,
                txtEvents,
                eventsLine,
                R.drawable.tab_events_icon_blue,
                ContextCompat.getColor(requireContext(), R.color.main_blue),
                View.VISIBLE
            )
        }
    }
}
