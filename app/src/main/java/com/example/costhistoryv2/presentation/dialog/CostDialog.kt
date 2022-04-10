package com.example.costhistoryv2.presentation.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.costhistoryv2.presentation.items.Inputs
import com.example.costhistoryv2.presentation.viewModels.CostDialogViewModel
import com.example.costhistoryv2.presentation.viewModels.CostHistoryViewModel
import com.example.costhistoryv2.ui.theme.Sizes
import org.koin.java.KoinJavaComponent.getKoin

object CostDialog {
    private val viewModel = getKoin().get<CostDialogViewModel>()
    @Composable
    fun Content() {
        Column(
            modifier = Modifier
                .padding(Sizes.paddingMiddle)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Sizes.paddingTiny)
        ) {
            Inputs.ItemStandardInput(
                textState = viewModel.title,
                required = true,
                title = "Имя",
                changeAction = {
                    viewModel.title.value = it
                }
            )
            Inputs.ItemStandardInput(
                textState = viewModel.title,
                required = true,
                title = "Имя",
                changeAction = {
                    viewModel.title.value = it
                }
            )
        }
    }
}