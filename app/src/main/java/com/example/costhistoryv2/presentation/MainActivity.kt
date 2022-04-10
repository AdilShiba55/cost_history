package com.example.costhistoryv2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.costhistoryv2.presentation.dialog.DialogUnit
import com.example.costhistoryv2.presentation.navigation.Navigator
import com.example.costhistoryv2.ui.theme.CostHistoryV2Theme
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin

class MainActivity : ComponentActivity() {
    var navigator = getKoin().get<Navigator>()
    var dialog = getKoin().get<DialogUnit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CostHistoryV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var scope = rememberCoroutineScope()
                    navigator.floatingButtonAction = {
//                        scope.launch {
//                            dialog.showCostCharacterDialog(action = {
//                                var point = ""
//                            })
//                        }
                    }
                    navigator.Content()
                    dialog.Content()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CostHistoryV2Theme {
        Greeting("Android")
    }
}