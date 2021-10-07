package com.okysoft.annictim.util.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DropDown(selectedValue: MutableState<String>,
             expanded: MutableState<Boolean>,
             dropDownList: List<String>) {
    if (dropDownList.isEmpty()) return
    Row(modifier = Modifier.clickable {
        expanded.value = !expanded.value
    }) {
        Text(text = selectedValue.value, fontSize = 18.sp)
        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
    }
    DropdownMenu(expanded = expanded.value,
        onDismissRequest = { expanded.value = false }) {
        dropDownList.forEach { item ->
            DropdownMenuItem(onClick = {
                expanded.value = false
                selectedValue.value = item
            }) {
                val style = if (item == selectedValue.value) {
                    MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.secondary
                    )
                } else {
                    MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Text(text = item, style = style)
            }
        }
    }
}