package com.ipca.wepet.presentation.fragment.animal

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.presentation.controller.AnimalActivity

@Composable
fun AnimalListItem(animal: AnimalModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, AnimalActivity::class.java).apply {
                    putExtra("animal", animal)
                }
                context.startActivity(intent)
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        animal.id?.let { resourceId ->
            Image(
                painter = painterResource(id = R.drawable.unknown_gender),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 8.dp)
            )
        }
        animal.name?.let {
            Text(
                text = it,
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 4.dp)
        ) {
            animal.gender?.let { gender ->
                val genderIcon = getGenderIcon(gender)
                Image(
                    painter = painterResource(id = genderIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            animal.breed?.let {
                Text(
                    text = it,
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun getGenderIcon(gender: String): Int {
    return when (gender) {
        "male" -> R.drawable.male_gender
        "female" -> R.drawable.female_gender
        else -> R.drawable.unknown_gender
    }
}
