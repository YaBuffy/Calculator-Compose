package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val navController = rememberNavController()
                val vm: CalculatorViewModel = viewModel(
                    factory = CalculatorViewModelFactory((LocalContext.current.applicationContext as App).historyDao)
                )
                val state = vm.state
                val history  by vm.history.collectAsState(emptyList())
                val buttonSpacing = 8.dp

                NavHost(navController = navController, startDestination = "calculator"){
                    composable("calculator"){
                        MainScreen(
                            state = state,
                            onAction = vm::onAction,
                            buttonSpacing = buttonSpacing,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background)
                                .systemBarsPadding(),
                            onHistoryClick = {navController.navigate("history")})
                    }
                    composable("history"){
                        HistoryScreen(
                            history,
                            onBack = {navController.popBackStack()})
                    }
                }
            }
        }
    }
}
