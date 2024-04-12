package ru.disspear574.notus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import ru.disspear574.notus.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val getNoteDao: NoteDao
        companion object {
            fun createDataBase(context: Context): NoteDatabase {
                return Room.databaseBuilder(
                    context = context,
                    NoteDatabase::class.java,
                    name = "note_db"
                ).build()
            }
        }
    }
