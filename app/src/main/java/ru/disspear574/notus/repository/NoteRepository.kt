package ru.disspear574.notus.repository

import ru.disspear574.notus.database.NoteDatabase
import ru.disspear574.notus.model.Note


typealias Notes = List<Note>

//class NoteRepository(private val db: NoteDatabase) {
//    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note = note)
//    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note = note)
//    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note = note)
//
//    fun getAllNotes() = db.getNoteDao().getAllNotes()
//    fun searchNote(query: String?) = db.getNoteDao().searchNote(query = query)
//}