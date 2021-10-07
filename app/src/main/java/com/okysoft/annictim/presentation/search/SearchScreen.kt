package com.okysoft.annictim.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okysoft.annictim.R
import com.okysoft.annictim.util.compose.DropDown
import java.text.SimpleDateFormat
import java.util.*

private fun createYearSpinnerList(): List<String> {
    val list = mutableListOf<String>()
    val dateFormat = SimpleDateFormat("yyyy", Locale.JAPAN)
    val currentYear = dateFormat.format(Date())
    val currentYearInt = currentYear.toInt()
    for (i in 1.downTo(-20)) {
        list.add((currentYearInt + i).toString() + '年')
    }
    list.add(0, "全体")
    return list
}

private val yearList = createYearSpinnerList()

@Preview(showSystemUi = true)
@Composable
fun SearchScreen(onClickSearchButton: (condition: SearchCondition) -> Unit = {}) {
    val seasonList = stringArrayResource(id = R.array.season_list).toList()
    val title = remember { mutableStateOf<String?>(null) }
    val year = remember { mutableStateOf<String>(yearList.first()) }
    val season = remember { mutableStateOf<String>(seasonList.first()) }
    val yearDropDownExpanded = remember { mutableStateOf(false) }
    val seasonDropDownExpanded = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(all = 16.dp)) {
        TextField(
            value = title.value ?: "",
            onValueChange = {
                title.value = it
            },
            label = {
                Text(text = "タイトル",
                    color = colorResource(id = R.color.colorAccent),
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedLabelColor = colorResource(id = R.color.colorAccent)
            ),
            singleLine = true
        )
        Column(modifier = Modifier.padding(top = 16.dp))  {
            Text(text = "年代",
                fontSize = 18.sp,
                color = colorResource(id = R.color.colorAccent))
            Spacer(modifier = Modifier.height(8.dp))
            DropDown(
                selectedValue = year,
                expanded = yearDropDownExpanded,
                dropDownList = yearList)
        }
        Column(modifier = Modifier.padding(top = 16.dp))  {
            Text(text = "季節",
                fontSize = 18.sp,
                color = colorResource(id = R.color.colorAccent))
            Spacer(modifier = Modifier.height(8.dp))
            DropDown(
                selectedValue = season,
                expanded = seasonDropDownExpanded,
                dropDownList = seasonList
            )
        }
        Button(
            onClick = {
                onClickSearchButton(SearchCondition(title.value, year.value, season.value))
            },
            modifier = Modifier.padding(vertical = 16.dp),
            colors =  ButtonDefaults.textButtonColors(
                backgroundColor = colorResource(id = R.color.colorAccent),
                contentColor = Color.White,
                disabledContentColor = Color.LightGray
            )
        ) {
            Text(
                text = stringResource(id = R.string.searching),
                fontSize = 18.sp
            )
        }
    }
}