package ru.disspear574.notus.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.disspear574.notus.ui.CustomScaffold
import ru.disspear574.notus.ui.CustomTF
import ru.disspear574.notus.viewmodel.NoteViewModel

class AddNoteScreen : Screen {
    @Composable
    override fun Content() = AddNoteScreenContent()
}

@Preview
@Composable
private fun AddNoteScreenContent() {
    val mainViewModel: NoteViewModel = viewModel(factory = NoteViewModel.factory)
    val navigator = LocalNavigator.currentOrThrow
    CustomScaffold(
        titleText = "Добавить Заметку",
        action = {
            Text(text = "SAVE", color = Color.White, modifier = Modifier
                .padding(10.dp)
                .clickable {
                    mainViewModel.insertNote()
                    navigator.pop()
                })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Добавить Заметку", style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTF(
                value = mainViewModel.titleText, modifier = Modifier.fillMaxWidth()
            )
            CustomTF(
                value = mainViewModel.descText,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            )
        }
    }
}