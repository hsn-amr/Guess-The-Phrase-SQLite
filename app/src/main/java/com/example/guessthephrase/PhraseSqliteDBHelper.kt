package com.example.guessthephrase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class PhraseSqliteDBHelper(context: Context): SQLiteOpenHelper(context, "Phrases.db",null,1) {

    private val sqliteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(p0: SQLiteDatabase?) {
        if(p0 != null){
            p0.execSQL("CREATE TABLE Phrase (text text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun saveNewPhrase(text: String):Long{
        val cv = ContentValues()
        cv.put("text", text)
        return sqliteDatabase.insert("Phrase",null,cv)
    }

    fun getAllPhrases():ArrayList<String>{
        val phrases = ArrayList<String>()
        val c: Cursor = sqliteDatabase.query("Phrase",null,null,null,null,null,null)
        if(c.moveToFirst()){
            do {
                phrases.add(c.getString(c.getColumnIndex("text")))
            }while (c.moveToNext())
        }
        return phrases
    }


}