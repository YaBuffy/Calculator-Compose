package com.example.calculator.historyDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {
    @Insert
    suspend fun insert(history: HistoryEntity)
    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<HistoryEntity>>
    @Query("DELETE FROM history")
    suspend fun clearHistory()
}