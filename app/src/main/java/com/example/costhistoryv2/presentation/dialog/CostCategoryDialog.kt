package com.example.costhistoryv2.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.costhistory.data.repositories.CostCategoryRepository
import com.example.costhistory.presentation.items.Buttons
import com.example.costhistory.presentation.items.EntityItems
import com.example.costhistory.units.IconUnit
import com.example.costhistory.units.ToastUnit
import com.example.costhistoryv2.R
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistoryv2.presentation.items.Inputs
import com.example.costhistoryv2.presentation.viewModels.CostCategoryDialogViewModel
import com.example.costhistoryv2.ui.theme.*
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin

object CostCategoryDialog {
    private val viewModel = getKoin().get<CostCategoryDialogViewModel>()
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Content() {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val focusManager = LocalFocusManager.current
        val dialogUnit = getKoin().get<DialogUnit>()

        Column(
            modifier = Modifier
                .padding(Sizes.paddingMiddle)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Sizes.paddingTiny)
        ) {
//        if (!viewModel.isNew()) {
//            Buttons.IconButton(
//                containerModifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentWidth(Alignment.End),
//                shape = CircleShape,
//                backgroundSize = 5.dp,
//                icon = R.drawable.ic_delete,
//                iconSize = Sizes.buttonTiny,
//                iconColor = Color.White,
//                backgroundColor = WeakRed,
//                onClickAction = {
//                    viewModel.removeCostCategory()
//                    scope.launch {
//                        dialogUnit.hideDialog()
//                    }
//                    focusManager.clearFocus()
//                })
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(10.dp)
//            )
//        }

            if(viewModel.costCategories.value.size > 0) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
                ) {
                    itemsIndexed(items = viewModel.costCategories.value) { index, item ->
                        EntityItems.CostCategoryItem(
                            item,
                            itemClick = {
                                viewModel.set(item.id)
                            },
                            itemLongClick = {
                            viewModel.removeCostCategory(item)
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
                        backgroundColor =
                        if (viewModel.selectedIconIndex.value != index) SimpleBlue else LightBlue,
                        onClickAction = {
                            val index =
                                if (viewModel.selectedIconIndex.value != index) index else -1
                            viewModel.selectedIconIndex.value = index
                        }
                    )
                }
            }

            Inputs.ItemStandardInput(
                textState = viewModel.title,
                required = true,
                title = "Заголовок",
                changeAction = {
                    viewModel.title.value = it
                }
            )
            Inputs.ItemStandardInput(
                textState = viewModel.description,
                title = "Описание",
                changeAction = {
                    viewModel.description.value = it
                }
            )
            Inputs.ItemStandardInput(
                textState = viewModel.currentCostTemplateTitle,
                title = "шаблон-заголовка-расхода",
                changeAction = {
                    viewModel.currentCostTemplateTitle.value = it
                },
                trailingIcon = {
                    Buttons.IconButton(
                        shape = RoundedCornerShape(0.dp),
                        backgroundSize = 18.dp,
                        icon = R.drawable.ic_add,
                        iconColor = Color.White,
                        backgroundColor = WeakGreen,
                        onClickAction = {
                            if (viewModel.currentCostTemplateTitle.value.trim()
                                    .isEmpty()
                            ) {
                                ToastUnit.showInfo(
                                    context = context,
                                    title = ToastUnit.VALIDATION_TITLE,
                                    message = "Поле не может быть пустым"
                                )
                            } else {
                                viewModel.addCostTemplateTitle()
                            }
                        }
                    )
                }
            )
            if (viewModel.costTemplateTitles.value.size > 0) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
                ) {
                    itemsIndexed(items = viewModel.costTemplateTitles.value) { index, item ->
                        Box(
                            modifier = Modifier
                                .clip(Shapes.small)
                                .clickable {
                                    viewModel.removeCostTemplateTitle(index)
                                },
                        ) {
                            Text(
                                modifier = Modifier
                                    .background(BoldBlue)
                                    .padding(4.dp),
                                text = item,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Buttons.StandardButton(
                text = if (viewModel.isNew()) "СОЗДАТЬ КАТЕГОРИЮ" else "РЕДАКТИРОВАТЬ КАТЕГОРИЮ",
                action = {
                    if (viewModel.title.value.trim().isEmpty()) {
                        ToastUnit.showInfo(
                            context = context,
                            title = ToastUnit.VALIDATION_TITLE,
                            message = ToastUnit.VALIDATION_MSG
                        )
                    } else {
                        focusManager.clearFocus()
                        viewModel.saveCostCategory()
//                        viewModel.fetchCostCategories()
//                    scope.launch {
//                        dialogUnit.hideDialog()
//                    }
//                    costHistoryViewModel.fetchCostCategories()
                    }
                })
        }
    }
}