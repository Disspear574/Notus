package ru.disspear574.notus.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.disspear574.notus.R
import ru.disspear574.notus.model.Note
import ru.disspear574.notus.ui.CustomAlert
import ru.disspear574.notus.ui.CustomScaffold
import ru.disspear574.notus.ui.CustomTF
import ru.disspear574.notus.viewmodel.NoteViewModel

class EditNoteScreen(val note: Note) : Screen {
    @Composable
    override fun Content() = EditNoteScreenContent(note)
}
@Composable
private fun EditNoteScreenContent(note: Note) {
    val mainViewModel: NoteViewModel = viewModel(factory = NoteViewModel.factory)
    val editTitle = remember { mutableStateOf(note.noteTitle) }
    val editDesc = remember { mutableStateOf(note.noteDesc) }
    val navigator = LocalNavigator.currentOrThrow
    val showAlert = remember { mutableStateOf(false) }
    CustomScaffold(titleText = note.noteTitle, isFloatingIcon = true, floatingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.baseline_done_24), contentDescription = ""
        )
    }, onFloatingButtonClick = {
        mainViewModel.nameNote = note
        mainViewModel.titleText = editTitle
        mainViewModel.descText = editDesc
        mainViewModel.insertNote()
        navigator.pop()
    }, action = {
        Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    showAlert.value = true
                })
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Редактирование Заметки", style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTF(
                value = editTitle, modifier = Modifier.fillMaxWidth()
            )
            CustomTF(
                value = editDesc, modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            )
        }
    }
    if (showAlert.value) {
        CustomAlert(onDismiss = showAlert) {
            mainViewModel.deleteNote(note)
            navigator.pop()
        }
    }
}