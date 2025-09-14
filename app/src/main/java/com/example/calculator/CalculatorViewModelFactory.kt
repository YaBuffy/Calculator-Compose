package com.example.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.historyDB.HistoryDAO

class CalculatorViewModelFactory(
    private val dao: HistoryDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalculatorViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

