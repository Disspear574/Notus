package ru.disspear574.notus.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.disspear574.notus.ui.theme.Pink

@Composable
fun CustomTF(value: MutableState<String>, modifier: Modifier) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier.border(1.dp, color = Pink, RoundedCornerShape(12.dp))
    )
}