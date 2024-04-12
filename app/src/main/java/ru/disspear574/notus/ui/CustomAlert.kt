package ru.disspear574.notus.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import ru.disspear574.notus.ui.theme.Pink
import ru.disspear574.notus.ui.theme.Red
@Composable
fun CustomAlert(onDismiss: MutableState<Boolean>, onClick: () -> Unit) {
    AlertDialog(
    containerColor = Color.White,
    title = {
        Text(text = "Удаление заметки")
    },
    text = {
        Text(text = "Вы точно хотите удалить заметку?")
    },
    onDismissRequest = {
        onDismiss.value = false
    },
    confirmButton = {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Red),

            onClick = onClick
        ) {
            Text("Удалить")
        }
    },
    dismissButton = {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Pink),
            onClick = {
                onDismiss.value = false
            }
        ) {
            Text("Отмена")
        }
    })
}