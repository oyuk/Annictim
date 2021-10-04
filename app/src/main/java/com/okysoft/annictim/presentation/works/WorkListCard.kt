package com.okysoft.annictim.presentation.works

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.okysoft.annictim.R
import com.okysoft.infra.extension.seasonText
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.type.SeasonName


private class PreviewWorkListProvider : PreviewParameterProvider<WorkFeed> {

    override val values: Sequence<WorkFeed>
        get() = sequenceOf(
                WorkFeed(
                    id = "1", annictId = 10, title = "title", seasonName = SeasonName.AUTUMN, seasonYear = 2019,
                    media = com.okysoft.infra.type.Media.MOVIE,
                    watchersCount = 10,
                    reviewsCount = 10,
                    image = null)
            )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WorkListCard(@PreviewParameter(PreviewWorkListProvider::class) workFeed: WorkFeed,
                 onClick: (Int) -> Unit = {}) {
    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .clickable(onClick = {
                onClick(workFeed.annictId)
            })
            .fillMaxWidth()
    ) {
        Row {
            Image(
                painter = rememberImagePainter(workFeed.image?.recommendedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(76.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    workFeed.title,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                val seasonText = workFeed.seasonText()
                if (!seasonText.isNullOrEmpty()) {
                    Text(
                        seasonText,
                        color = colorResource(R.color.colorAccent),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye_24dp),
                        contentDescription = null,
                    )
                    Text(
                        workFeed.watchersCount.toString(),
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_create_24dp),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        workFeed.reviewsCount.toString(),
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
        }
    }
}