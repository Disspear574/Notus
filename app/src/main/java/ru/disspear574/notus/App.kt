package ru.disspear574.notus

import android.app.Application
import ru.disspear574.notus.database.NoteDatabase

class App: Application() {
    val database by lazy {NoteDatabase.createDataBase(this)}
}