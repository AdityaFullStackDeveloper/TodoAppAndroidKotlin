package com.example.todoapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TodoDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "todo_database"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
        const val COLUMN_DESC = "desc"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_DESC TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUserNotes(userNote: UserNotesModel) {
        val insertDataBase = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DESC, userNote.userDesc)
        }
        insertDataBase.insert(TABLE_NAME, null, values)
        insertDataBase.close()
    }

    @SuppressLint("Range")
    fun insertedAllNotes(): List<UserNotesModel> {
        val notesList = mutableListOf<UserNotesModel>()
        val dataBase = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = dataBase.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC))

            val notes = UserNotesModel(id, desc)
            notesList.add(notes)
        }

        cursor.close()
        return notesList
    }

    fun updateNotes(userNoteList: UserNotesModel){
        val dataBase = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DESC, userNoteList.userDesc)
        }
        val clause = "$COLUMN_ID =?"
        val arguments = arrayOf(userNoteList.userId.toString())
        dataBase.update(TABLE_NAME, values, clause, arguments)
    }

    @SuppressLint("Range")
    fun getNoteById(notesId: Int):UserNotesModel{
        val dataBase = writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $notesId"
        val cursor = dataBase.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        val descUpdate = cursor.getString(cursor.getColumnIndex(COLUMN_DESC))

        cursor.close()
        return UserNotesModel(id, descUpdate)
    }

    fun deleteNotes(notesId: Int){
        val deleteDataBase = writableDatabase
        val clause = "$COLUMN_ID = ?"
        val arguments = arrayOf(notesId.toString())
        deleteDataBase.delete(TABLE_NAME, clause, arguments)

    }
}
