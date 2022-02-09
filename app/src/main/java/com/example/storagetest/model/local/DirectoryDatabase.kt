package com.example.storagetest.model.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext

class DirectoryDatabase(

    private val context: Context, //application conetxt
    private val fileName: String,
    private val factory: SQLiteDatabase.CursorFactory? = null,
    private val version: Int  //!define the current database schema

) : SQLiteOpenHelper(context,fileName,factory,version) {

    /**
     * Tocreate the current table schema
     * define the tables theat belongs to this Database
     */

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE PersonTable (" +
                    "first_name VARCHAR(255),last_name VARCHAR(255),email VARCHAR(255),phone_number VARCHAR(255));"
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}