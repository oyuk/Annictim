package com.okysoft.annictim.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okysoft.annictim.R
import com.okysoft.annictim.util.compose.Progress

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(showLoading: MutableState<Boolean>, onClick: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorPrimary)
    )) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 65.sp,
                color = colorResource(id = R.color.grey_50)
            )
            Button(
                onClick = onClick,
                modifier = Modifier.padding(vertical = 10.dp),
                colors =  ButtonDefaults.textButtonColors(
                    backgroundColor = colorResource(id = R.color.colorAccent),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                    disabledContentColor = LightGray)
            ) {
                Text(
                    text = stringResource(id = R.string.login_or_create),
                    fontSize = 18.sp
                )
            }
        }
        if (showLoading.value) {
            Progress()
        }
    }
}