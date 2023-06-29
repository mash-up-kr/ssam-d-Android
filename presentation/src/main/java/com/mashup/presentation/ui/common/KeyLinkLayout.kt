package com.mashup.presentation.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.Gray02

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KeyLinkBottomSheetLayout(
    bottomSheetContent: @Composable () -> Unit,
    scaffoldLayout: @Composable () -> Unit
) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Gray02,
        sheetContent = { bottomSheetContent() },
    ) {
        scaffoldLayout()
    }
}