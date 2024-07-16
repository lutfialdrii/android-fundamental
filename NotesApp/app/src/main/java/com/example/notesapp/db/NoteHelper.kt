package com.example.notesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.notesapp.db.DatabaseContract.NoteColumns.Companion._ID
import java.sql.SQLException

class NoteHelper(context: Context) {
    private var databasHelper: DatabaseHelper = DatabaseHelper(context)

    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = DatabaseContract.NoteColumns.TABLE_NAME
        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper = INSTANCE ?: synchronized(this) {
            INSTANCE ?: NoteHelper(context)
        }
    }

    @Throws(SQLException::class)
    fun open() {
        database = databasHelper.writableDatabase
    }
    @Throws(SQLException::class)
    fun close() {
        databasHelper.close()

        if (database.isOpen)
            database.close()
    }
    @Throws(SQLException::class)
    fun queryAll(): Cursor {
        return database.query(DATABASE_TABLE, null, null, null, null, null, "$_ID ASC")
    }
    @Throws(SQLException::class)
    fun queryById(id: String): Cursor {
        return database.query(DATABASE_TABLE, null, "$_ID = ?", arrayOf(id), null, null, null)
    }
    @Throws(SQLException::class)
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
    @Throws(SQLException::class)
    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }
    @Throws(SQLException::class)
    fun deleytById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
    }

}