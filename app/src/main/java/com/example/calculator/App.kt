package com.example.calculator

import android.app.Application
import com.example.calculator.historyDB.AppDatabase

class App: Application() {
    val database by lazy { AppDatabase.Companion.createDatabase(this) }
    val historyDao by lazy { database.historyDao() }
}