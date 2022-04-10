//package com.example.costhistoryv2.presentation.screens.cost_history.blocks
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.Shapes
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.zIndex
//import com.example.costhistory.presentation.items.Buttons
//import com.example.costhistory.presentation.items.EntityItems
//import com.example.costhistoryv2.R
//import com.example.costhistoryv2.data.entities.CostCategory
//import com.example.costhistoryv2.data.entities.CostCharacter
//import com.example.costhistoryv2.presentation.dialog.DialogUnit
//import com.example.costhistoryv2.presentation.items.NoItems
//import com.example.costhistoryv2.presentation.viewModels.CostCategoryDialogViewModel
//import com.example.costhistoryv2.presentation.viewModels.CostCharacterDialogViewModel
//import com.example.costhistoryv2.presentation.viewModels.CostHistoryViewModel
//import com.example.costhistoryv2.ui.theme.Shapes
//import com.example.costhistoryv2.ui.theme.SimpleGray
//import com.example.costhistoryv2.ui.theme.Sizes
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//import org.koin.java.KoinJavaComponent.getKoin
//
//
//val dialogUnit = getKoin().get<DialogUnit>()
//val costHistoryViewModel = getKoin().get<CostHistoryViewModel>()
//val costCategoryDialogViewModel = getKoin().get<CostCategoryDialogViewModel>()
//val costCharacterDialogViewModel = getKoin().get<CostCharacterDialogViewModel>()
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun CostHistoryScreenParamsBlock() {
//
//    CategoryParam()
//
//    Spacer(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(10.dp)
//    )
//
//    CharactersParam()
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//private fun CategoryParam() {
//    val scope = rememberCoroutineScope()
//    val costCategories = remember { viewModel.costCategories }
//
//    if (costCategories.value.isEmpty()) {
//        NoItems.Standard(
//            title = "Категории не найдено",
//            description = "Добавляйте категории ваших расходов (еда, такси и т.д)",
//            withAddButton = true
//        ) {
//            scope.launch {
//                dialogUnit.showCostCategoryDialog()
//            }
////            costHistoryViewModel.openCategoryDialog(scope)
//        }
//    } else {
//        CategoryParamContent(
//            costCategories = costCategories.value,
//            scope = scope
//        )
//    }
//}
//
//@Composable
//private fun CategoryParamContent(
//    costCategories: List<CostCategory>,
//    scope: CoroutineScope
//) {
//    Box(
//        modifier = Modifier
//            .clip(Shapes.small)
//            .fillMaxWidth()
//            .background(SimpleGray)
//            .padding(Sizes.paddingSmall)
//    ) {
//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
//        ) {
//            itemsIndexed(items = costCategories) { index, item ->
//                val itemClick: () -> Unit = {
//                    scope.launch {
//                        viewModel.set
//                        dialogUnit.showCostCategoryDialog(action = {
//                            viewModel.fetchCostCategories()
//                        })
//                    }
//                }
//                EntityItems.CostCategoryItem(
//                    item, itemClick
//                )
//            }
//        }
//        Buttons.IconButton(
//            icon = R.drawable.ic_add,
//            containerModifier = Modifier
//                .zIndex(1f)
//                .offset(y = -Sizes.paddingSmall, x = Sizes.paddingSmall)
//                .align(alignment = Alignment.TopEnd),
//            onClickAction = {
//                scope.launch {
//                    dialogUnit.showCostCategoryDialog()
//                }
//            }
//        )
//    }
//}
//
//@Composable
//private fun CharactersParam() {
//    val costCharacters = remember { viewModel.costCharacters }
//    val scope = rememberCoroutineScope()
//
//    if (costCharacters.value.isEmpty()) {
//        NoItems.Standard(
//            title = "Персонажей не найдено",
//            description = "Добавляйте тех, на кого тратите деньги (люди/животные)",
//            withAddButton = true
//        ) {
//            scope.launch {
//                dialogUnit.showCostCharacterDialog(action = {
//
//                })
//            }
//        }
//    } else {
//        CharactersParamContent(
//            costCharacters = costCharacters.value,
//            scope = scope
//        )
//    }
//}
//
//@Composable
//private fun CharactersParamContent(
//    costCharacters: List<CostCharacter>,
//    scope: CoroutineScope
//) {
//    Box(
//        modifier = Modifier
//            .clip(Shapes.small)
//            .fillMaxWidth()
//            .background(SimpleGray)
//            .padding(
//                top = Sizes.paddingMiddle, bottom = Sizes.paddingMiddle,
//                start = Sizes.paddingSmall, end = Sizes.paddingSmall
//            )
//    ) {
//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(Sizes.paddingSmall)
//        ) {
//            itemsIndexed(items = costCharacters) { index, item ->
//                val itemClick: () -> Unit = {
//                    scope.launch {
//                        dialogUnit.showCostCharacterDialog(action = {
//
//                        })
//
//                    }
//                }
//                EntityItems.CostCharacterItem(
//                    costCharacter = item,
//                    itemClick = itemClick
//                )
//            }
//        }
//        Buttons.IconButton(
//            icon = R.drawable.ic_add,
//            containerModifier = Modifier
//                .zIndex(1f)
//                .offset(y = -Sizes.paddingMiddle, x = Sizes.paddingSmall)
//                .align(alignment = Alignment.TopEnd),
//            onClickAction = {
//                scope.launch {
//                    dialogUnit.showCostCharacterDialog(action = {
//
//                    })
//                }
//            }
//        )
//    }
//}