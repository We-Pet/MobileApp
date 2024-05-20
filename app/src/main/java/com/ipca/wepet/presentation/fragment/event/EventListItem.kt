import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipca.wepet.domain.model.EventModel

@Composable
fun EventListItem(event: EventModel) {
    event.id?.let {
        Text(
        text = it,
        style = TextStyle(fontSize = 18.sp),
        modifier = Modifier.padding(8.dp)
    )
    }
}
