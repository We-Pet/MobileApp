import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.ShelterModel
import com.ipca.wepet.presentation.controller.ShelterActivity

@Composable
fun ShelterListItem(shelter: ShelterModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, ShelterActivity::class.java).apply {
                    putExtra("shelter", shelter)
                }
                context.startActivity(intent)
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = if (shelter.profileImageUrl != null) {
            rememberImagePainter(shelter.profileImageUrl)
        } else {
            painterResource(id = R.drawable.unknown_gender)
        }

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
                .padding(bottom = 8.dp)
        )
        shelter.name?.let {
            Text(
                text = it,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}