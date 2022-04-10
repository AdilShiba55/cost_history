package com.example.costhistoryv2.presentation.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.costhistory.data.repositories.CurrencyRepository
import com.example.costhistoryv2.data.entities.CostCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

class DialogUnit {
    private val CATEGORY_DIALOG: Int = 0
    private val CHARACTER_DIALOG: Int = 1
    private val COST_DIALOG: Int = 2

    @OptIn(ExperimentalMaterialApi::class)
    private var _dialogState: MutableLiveData<ModalBottomSheetState> = MutableLiveData()
    private var dialogId = mutableStateOf(-1)
    private var dialogClosedAction: () -> Unit = {}

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Content() {
        rememberCoroutineScope()
        _dialogState.value = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//        val dialogId by currentId

        var dialog: @Composable() () -> Unit =
            when(dialogId.value) {
                CHARACTER_DIALOG -> {
                    { CostCharacterDialog.Content() }
                }
                COST_DIALOG -> {
                    { CostDialog.Content() }
                }
                else -> {
                    { CostCategoryDialog.Content() }
                }
            }
        DialogContainer(
            modalBottomSheetState = _dialogState.value?: rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            dialog
        )
    }

    suspend fun showCostCategoryDialog(
        action: () -> Unit = {}
    ) {
        dialogClosedAction = action
        dialogId.value = CATEGORY_DIALOG
        showDialog()
    }
    suspend fun showCostCharacterDialog(
        action: () -> Unit
    ) {
        dialogClosedAction = action
        dialogId.value = CHARACTER_DIALOG
        showDialog()
    }
    suspend fun showCostDialog(
        action: () -> Unit
    ) {
        dialogClosedAction = action
        dialogId.value = COST_DIALOG
        showDialog()
    }

    @OptIn(ExperimentalMaterialApi::class)
    private suspend fun showDialog() {
        _dialogState.value?.animateTo(ModalBottomSheetValue.Expanded)
    }
    @OptIn(ExperimentalMaterialApi::class)
    suspend fun hideDialog() {
        _dialogState.value?.hide()
        dialogId.value = -1
        dialogClosedAction()
        dialogClosedAction = {}
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun DialogContainer(
        modalBottomSheetState: ModalBottomSheetState,
        content: @Composable() () -> Unit
    ) {
        ModalBottomSheetLayout(
//                modifier = Modifier.padding(bottom = Sizes.bottomNavHeight),
            sheetState = modalBottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetContent = {
                content()
            }
        ) {}
    }
}