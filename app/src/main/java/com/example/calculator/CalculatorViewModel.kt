package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.historyDB.HistoryDAO
import com.example.calculator.historyDB.HistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CalculatorViewModel(private val dao: HistoryDAO): ViewModel() {

    var state by mutableStateOf(CalculatorState())

    val history: Flow<List<HistoryEntity>> = dao.getAllHistory()

    fun onAction(action: CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when{
            state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
            state.operation != null -> state =  state.copy(operation = null)
            state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number2 != null && number1 != null){
            val result = when(state.operation){
                is CalculatorOperation.Add -> number1+number2
                is CalculatorOperation.Subtract -> number1-number2
                is CalculatorOperation.Divide -> number1/number2
                is CalculatorOperation.Multiply -> number1*number2
                null -> return
            }

            val expression = "${state.number1} ${state.operation?.symbol ?: ""} ${state.number2}"
            val historyEntity = HistoryEntity(expression = expression, result = result.toString().take(8))

            viewModelScope.launch {
                dao.insert(historyEntity)
            }

            state = state.copy(
                number1 = result.toString().take(8),
                operation = null)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()) state = state.copy(operation = operation)
    }

    private fun enterDecimal() {
        if(state.operation == null && state.number1.isNotBlank() && !state.number1.contains(".")){
            state.copy(number1 = state.number1 + ".")
            return
        }
        if(state.number2.isNotBlank() && !state.number2.contains(".")){
            state.copy(number2 = state.number2 + ".")
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGHT) return
            state = state.copy(number1 = state.number1 + number.toString())
            return
        }
        if(state.number2.length >= MAX_NUM_LENGHT) return
        state = state.copy(number2 = state.number2 + number.toString())
    }

    companion object{
        private const val MAX_NUM_LENGHT = 8
    }


}