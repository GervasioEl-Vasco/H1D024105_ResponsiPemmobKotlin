package com.example.responsipemmobkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.responsipemmobkotlin.navigation.AppNavGraph
import com.example.responsipemmobkotlin.ui.theme.ResponsiPemmobKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResponsiPemmobKotlinTheme {
                AppNavGraph()
            }
        }
    }
}