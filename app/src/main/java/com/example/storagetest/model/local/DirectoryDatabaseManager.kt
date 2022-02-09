package com.example.storagetest.model.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DirectoryDatabaseManager(private val context: Context) {

    private val directoryDB : DirectoryDatabase by lazy {
        DirectoryDatabase(context,"directory",version= 1)
    }

    private val readDB: SQLiteDatabase by lazy {
        directoryDB.readableDatabase
    }
    private val writeDB: SQLiteDatabase by lazy {
        directoryDB.writableDatabase
    }

    fun getData() : List<PersonTable>{
        //execute the android-way using cursor
        val tableName = "PersonTable"

       val columns = arrayOf(
           PersonSchema.FIRSTNAME,PersonSchema.LASTNAME,PersonSchema.EMAIL,PersonSchema.PHONENUMBER
       )

        val where = "${PersonSchema.EMAIL} LIKE ?"
        val search = arrayOf("% input %")
       val cursor =  readDB.query(tableName,columns,null,null,null,null,null)

        val listOfPersons  = mutableListOf<PersonTable>()
        while (cursor.moveToNext()){
            val firstName = cursor.getString(cursor.getColumnIndexOrThrow(PersonSchema.FIRSTNAME))
            val lastName = cursor.getString(cursor.getColumnIndexOrThrow(PersonSchema.LASTNAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(PersonSchema.EMAIL))
            val phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(PersonSchema.PHONENUMBER))
            listOfPersons.add(
                PersonTable(firstName,lastName,email, phoneNumber)
            )
        }
        cursor.close()
        return listOfPersons
    }

    fun insertData(person:PersonTable):Boolean{

        val contentValues = ContentValues()
        contentValues.put(PersonSchema.FIRSTNAME,person.firstName)
        contentValues.put(PersonSchema.LASTNAME,person.lastName)
        contentValues.put(PersonSchema.EMAIL,person.email)
        contentValues.put(PersonSchema.PHONENUMBER,person.phoneNumber)


        val result = writeDB.insertOrThrow("PersonTable",null,contentValues)
        //writeDB.insertWithOnConflict("",null,contentValues,SQLiteDatabase.CONFLICT_FAIL)
        return result > 0
    }
}