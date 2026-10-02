package com.example.vmpsharedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vmpsharedemo.ui.RequestsScreen
import com.example.vmpsharedemo.ui.theme.VMPShareDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VMPShareDemoTheme {
                RequestsScreen()
            }
        }
    }
}