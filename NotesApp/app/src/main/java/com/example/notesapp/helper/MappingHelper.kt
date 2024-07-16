package com.example.notesapp.helper

import android.database.Cursor
import com.example.notesapp.db.DatabaseContract
import com.example.notesapp.entity.Note

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<Note> {
        val notesList = ArrayList<Note>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val desc =
                    getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                notesList.add(Note(id, title, desc, date))
            }
        }
        return notesList
    }
}