package com.example.costhistoryv2.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.costhistory.presentation.items.Buttons
import com.example.costhistory.presentation.items.EntityItems
import com.example.costhistory.units.ToastUnit
import com.example.costhistoryv2.R
import com.example.costhistoryv2.presentation.items.Inputs
import com.example.costhistoryv2.presentation.viewModels.CostCategoryDialogViewModel
import com.example.costhistoryv2.presentation.viewModels.CostCharacterDialogViewModel
import com.example.costhistoryv2.ui.theme.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin

object CostCharacterDialog {
    private val viewModel = getKoin().get<CostCharacterDialogViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Content() {
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val dialogUnit = getKoin().get<DialogUnit>()

        Column(
            modifier = Modifier
                .padding(Sizes.paddingMiddle)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Sizes.paddingTiny)
        ) {
//        Column(
//            modifier = Modifier
//                .padding(Sizes.paddingMiddle)
//                .verticalScroll(rememberScrollState())
//        ) {
//            if (!viewModel.isNew()) {
//                Buttons.IconButton(
//                    containerModifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentWidth(Alignment.End),
//                    shape = CircleShape,
//                    backgroundSize = 5.dp,
//                    icon = R.drawable.ic_delete,
//                    iconSize = Sizes.buttonTiny,
//                    iconColor = Color.White,
//                    backgroundColor = WeakRed,
//                    onClickAction = {
//                        viewModel.removeCostCharacter()
//                        scope.launch {
//                            dialogUnit.hideDialog()
//                        }
//                        focusManager.clearFocus()
//                    })
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(10.dp)
//                )
//            }

            if (viewModel.costCharacters.value.size > 0) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
                ) {
                    itemsIndexed(items = viewModel.costCharacters.value) { index, item ->
                        EntityItems.CostCharacterItem(
                            item,
                            itemClick = {
                                viewModel.set(item.id)
                            },
                            itemLongClick = {
//                                viewModel.removeCostCharacter(item)
                            })
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(SimpleGray)
                )
            }

            val listState = rememberLazyListState()

            LazyRow(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
            ) {
                scope.launch {
                    listState.animateScrollToItem(index = viewModel.selectedIconIndex.value)
                }
                itemsIndexed(items = viewModel.icons) { index, item ->
                    Buttons.IconButton(
                        shape = Shapes.medium as RoundedCornerShape,
                        backgroundSize = 15.dp,
                        icon = item,
                        iconColor = Color.White,
                        backgroundColor = if (viewModel.selectedIconIndex.value != index) SimpleBlue else LightBlue,
                        onClickAction = {
                            viewModel.selectedIconIndex.value =
                                if (viewModel.selectedIconIndex.value != index) index else -1
                        }
                    )
                }
            }
            Inputs.ItemStandardInput(
                textState = viewModel.title,
                required = true,
                title = "Имя",
                changeAction = {
                    viewModel.title.value = it
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            Buttons.StandardButton(
                text = if (viewModel.isNew()) "СОЗДАТЬ ПЕРСОНАЖА" else "РЕДАКТИРОВАТЬ ПЕРСОНАЖА",
                action = {
                    if (viewModel.title.value.trim().isEmpty()) {
                        ToastUnit.showInfo(
                            context = context,
                            title = ToastUnit.VALIDATION_TITLE,
                            message = ToastUnit.VALIDATION_MSG
                        )
                    } else {
                        focusManager.clearFocus()
                        viewModel.saveCostCharacter()
//                    scope.launch {
//                        dialogUnit.hideDialog()
//                    }
                    }
                })
        }
    }
}