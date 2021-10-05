package com.okysoft.annictim.presentation.widget.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

sealed class DialogAction {
    abstract val title: String
    abstract val action: () -> Unit

    data class Positive(override val title: String, override val action: () -> Unit): DialogAction()
    data class Negative(override val title: String, override val action: () -> Unit): DialogAction()
}

@Composable
fun CustomDialog(title: String,
                 message: String?,
                 positiveAction: DialogAction.Positive,
                 negativeAction: DialogAction.Negative?,
                 openDialog: MutableState<Boolean>) {
    if (!openDialog.value) return
    AlertDialog(
        onDismissRequest = {  openDialog.value = false },
        title = {
            Text(text = title)
        },
        text = {
            message?.let { Text(text = it) }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    positiveAction.action()
                }
            ) {
                Text(text = positiveAction.title)
            }
        },
        dismissButton = {
            negativeAction?.let {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        it.action()
                    }
                ) {
                    Text(text = it.title)
                }
            }
        }
    )
}