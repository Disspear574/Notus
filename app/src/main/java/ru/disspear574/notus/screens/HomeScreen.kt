package ru.disspear574.notus.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.disspear574.notus.R
import ru.disspear574.notus.model.Note
import ru.disspear574.notus.ui.CustomScaffold
import ru.disspear574.notus.ui.CustomSearchBar
import ru.disspear574.notus.ui.NotesCard
import ru.disspear574.notus.ui.SearchItems
import ru.disspear574.notus.viewmodel.NoteViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() = HomeScreenContent()
}

@Preview
@Composable
private fun HomeScreenContent() {
    val mainViewModel: NoteViewModel = viewModel(factory = NoteViewModel.factory)
    val notesList = mainViewModel.getAllNotes.collectAsState(initial = emptyList())
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    var answer: List<Note> = notesList.value
    val navigator = LocalNavigator.currentOrThrow
    val showSearchBar = remember { mutableStateOf(false) }
    val activeSearchBar = remember { mutableStateOf(false) }
    fun search(asd: String?) {
        mainViewModel.searchNN(asd).observe(lifecycleOwner) { list -> answer = list }
    }
    CustomScaffold(titleText = "Заметки", isFloatingIcon = true, floatingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.outline_add_24), contentDescription = ""
        )
    }, onFloatingButtonClick = {
        navigator.push(AddNoteScreen())
    }, action = {
        Icon(painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    showSearchBar.value = !showSearchBar.value
                    activeSearchBar.value = false
                })
    }) { padding ->
        Column(
            modifier = Modifier.padding(top = padding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(showSearchBar.value) {
                CustomSearchBar(query = mainViewModel.searchTextField.value, onsearch = {
                    search(it)
                    mainViewModel.searchTextField.value = it
                }, isActive = activeSearchBar, onClear = { mainViewModel.clearSearch() }) {
                    LazyColumn {
                        items(answer) {
                            SearchItems(it = it, navigator = navigator)
                        }
                    }
                }
            }
            Crossfade(targetState = notesList.value.isEmpty(), label ="") {
                if (it) {
                    Image(
                        painter = painterResource(id = R.drawable.bookimage),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
                        items(notesList.value) { note ->
                            NotesCard(note = note) {
                                navigator.push(EditNoteScreen(note = note))
                            }
                        }
                    }
                }
            }
        }
    }
}