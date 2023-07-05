package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KeyLinkBottomSheetSample() {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            KeyLinkKeywordBottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
                matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "매쉬업", "일상", "디자인", "IT", "취준")
            )
        },
        modalSheetState = modalSheetState
    ) {
        Scaffold {
            Box(
                modifier = Modifier.padding(it)
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (modalSheetState.isVisible) modalSheetState.hide()
                            else modalSheetState.show()
                        }
                    },
                ) {
                    Text(text = "Open Sheet")
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0XFFFFFFFF)
@Composable
fun BottomSheetPreview() {
    SsamDTheme {
        KeyLinkBottomSheetSample()
    }
}