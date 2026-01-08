package com.digia.digiauiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.digia.digiaui.app.DigiaUIApp
import com.digia.digiaui.framework.DUIFactory

import com.digia.digiauiexample.ui.theme.DigiaUIExampleTheme
import com.digia.digiaui.init.DigiaUI
import com.digia.digiaui.init.DigiaUIOptions
import com.digia.digiaui.init.Flavor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigiaUIExampleTheme {
                val appConfigState = remember { mutableStateOf<DigiaUI?>(null) }
                if (appConfigState.value != null) {
                    DigiaUIApp(
                        digiaUI = appConfigState.value!!,

                        content = {
                            Scaffold(modifier = Modifier.fillMaxSize()) {
                                innerPadding ->
                                Greeting(
                                    name = "Digia UI Initialized",
                                    modifier = Modifier.padding(innerPadding)
                                )
                                DUIFactory.getInstance().CreateInitialPage()
                            }
                        }
                    )
                } else {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Loading...",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
                LaunchedEffect(Unit) {
                    try {
                        val appConfig = DigiaUI.initialize(options = DigiaUIOptions(
                            context = this@MainActivity,
                            flavor = Flavor.Debug(),
                            accessKey = "69538d36c16b125c10d2b296"  // Replace with actual access key
                        ))
                        appConfigState.value = appConfig
                    } catch (e: Exception) {
                        appConfigState.value = null
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DigiaUIExampleTheme {
        Greeting("Android")
    }
}