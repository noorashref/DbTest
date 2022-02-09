package com.example.storagetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.storagetest.model.local.DirectoryDatabaseManager
import com.example.storagetest.model.local.PersonTable

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DirectoryDatabaseManager(applicationContext).run {
            insertData(
                PersonTable(
                    "Ashref Ali",
                    "Ameer Basha",
                    "email@email.com",
                    "9790214566"
                )
            )
            val response = getData()
            Log.d(TAG, "onCreate: $response")
        }

    }
}