package ru.disspear574.notus.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.disspear574.notus.model.Note
import ru.disspear574.notus.ui.theme.Pink
import ru.disspear574.notus.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesCard(note: Note, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = Pink),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = note.noteTitle, color = Red, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Paragraph
                )
            )
            Text(text = note.noteDesc, color = Pink, fontSize = 14.sp, maxLines = 1)
        }
    }
}