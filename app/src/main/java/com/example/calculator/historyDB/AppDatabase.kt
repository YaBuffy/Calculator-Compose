package com.example.calculator.historyDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDAO
    companion object{
        fun createDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "history"
            ).build()
        }
    }
}