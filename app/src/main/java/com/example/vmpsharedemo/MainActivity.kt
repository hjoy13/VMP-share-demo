package com.example.vmpsharedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.vmpsharedemo.ui.CreateRequestScreen
import com.example.vmpsharedemo.ui.RequestsScreen
import com.example.vmpsharedemo.ui.theme.VMPShareDemoTheme

// The two screens of the demo. A simple state switch is enough, no navigation library.
private enum class Screen { REQUESTS, CREATE }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VMPShareDemoTheme {
                var screen by remember { mutableStateOf(Screen.REQUESTS) }

                // Back from the form returns to the list instead of closing the app
                BackHandler(enabled = screen == Screen.CREATE) {
                    screen = Screen.REQUESTS
                }

                when (screen) {
                    Screen.REQUESTS -> RequestsScreen(
                        onAskForBlood = { screen = Screen.CREATE }
                    )
                    Screen.CREATE -> CreateRequestScreen(
                        onBack = { screen = Screen.REQUESTS },
                        onDone = { screen = Screen.REQUESTS }
                    )
                }
            }
        }
    }
}
