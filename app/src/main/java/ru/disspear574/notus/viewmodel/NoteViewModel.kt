package ru.disspear574.notus.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.disspear574.notus.App
import ru.disspear574.notus.database.NoteDatabase
import ru.disspear574.notus.model.Note

@Suppress("UNCHECKED_CAST")
class NoteViewModel(private val database: NoteDatabase) : ViewModel() {

    val getAllNotes = database.getNoteDao.getAllNotes()
    var titleText = mutableStateOf("")
    var descText = mutableStateOf("")
    var searchTextField = mutableStateOf("")
    var nameNote: Note? = null

    fun insertNote() = viewModelScope.launch {
        val nameNotee = nameNote?.copy(noteTitle = titleText.value, noteDesc = descText.value)
            ?: Note(noteTitle = titleText.value, noteDesc = descText.value)
        database.getNoteDao.insertNote(nameNotee)
    }

    fun deleteNote(nameNote: Note) = viewModelScope.launch {
        database.getNoteDao.deleteNote(nameNote)
    }

    fun searchNN(field: String?) = database.getNoteDao.searchNote("%${field}")

    fun clearSearch() {
        searchTextField.value = ""
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return NoteViewModel(database) as T
            }
        }
    }
}