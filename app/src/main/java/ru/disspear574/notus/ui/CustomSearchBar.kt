package ru.disspear574.notus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import ru.disspear574.notus.model.Note
import ru.disspear574.notus.screens.EditNoteScreen
import ru.disspear574.notus.ui.theme.LightPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    query: String,
    onsearch: (String) -> Unit,
    isActive: MutableState<Boolean>,
    onClear: () -> Unit,
    content: @Composable (ColumnScope) -> Unit
) {
    SearchBar(query = query,
        onQueryChange = onsearch,
        onSearch = onsearch,
        active = isActive.value,
        onActiveChange = { isActive.value = it },
        tonalElevation = 6.dp,
        placeholder = { Text(text = "Найти заметку...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search, contentDescription = ""
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Clear,
                contentDescription = "",
                modifier = Modifier.clickable(onClick = onClear)
            )
        },
        colors = SearchBarDefaults.colors(containerColor = LightPink),
        modifier = Modifier.padding(vertical = 0.dp),
        content = content
    )
}
@Composable
fun SearchItems(it: Note, navigator: Navigator) {
    Divider(modifier = Modifier.padding(start = 10.dp))
    Text(text = it.noteTitle,
        modifier = Modifier
            .padding(10.dp)
            .clickable { navigator.push(EditNoteScreen(note = it)) })
}