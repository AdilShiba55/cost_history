package com.example.costhistoryv2.presentation.screens.cost_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.costhistory.presentation.items.Buttons
import com.example.costhistory.units.ToastUnit
import com.example.costhistoryv2.presentation.dialog.DialogUnit
import com.example.costhistoryv2.presentation.items.ExpandContent
import com.example.costhistoryv2.presentation.navigation.Navigator
import com.example.costhistoryv2.presentation.viewModels.CostHistoryViewModel
import com.example.costhistoryv2.ui.theme.BoldBlue
import com.example.costhistoryv2.ui.theme.SimpleGray
import com.example.costhistoryv2.ui.theme.Sizes
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin


object CostHistoryScreen {
    val navigator = getKoin().get<Navigator>()
    val dialogUnit = getKoin().get<DialogUnit>()
    val viewModel = getKoin().get<CostHistoryViewModel>()
    @Composable
    fun Content() {
        val scope = rememberCoroutineScope()
        navigator.floatingButtonAction = {
            scope.launch {
                dialogUnit.showCostDialog(action = {})
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SimpleGray)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Sizes.paddingSmall),
            ) {
                item() {
                    Row(modifier = Modifier
                        .padding(Sizes.paddingTiny),
                        horizontalArrangement = Arrangement.spacedBy(Sizes.paddingTiny)
                    ) {
                        Box(modifier = Modifier.weight(1F)) {
                            Buttons.StandardButton(
                                text = "Categories",
                                backgroundColor = BoldBlue,
                                action = {
                                    scope.launch {
                                        dialogUnit.showCostCategoryDialog(action = {})
                                    }
                                })
                        }
                        Box(modifier = Modifier.weight(1F)) {
                            Buttons.StandardButton(
                                text = "Characters",
                                backgroundColor = BoldBlue,
                                action = {
                                    scope.launch {
                                        dialogUnit.showCostCharacterDialog(action = {})
                                    }
                                })
                        }
                    }
                }
                items(50){
                    Text(
                        text = "item $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                    )
                }
            }

        }
    }
}