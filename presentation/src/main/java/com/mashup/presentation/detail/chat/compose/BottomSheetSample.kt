package com.mashup.presentation.detail.chat.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetSample() {
    val coroutineScope = rememberCoroutineScope()
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

        sheetContent = {
            BottomSheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            )
        },
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

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        BottomSheetItem(
            actionTextId = R.string.bottom_sheet_disconnect,
            actionDrawableId = R.drawable.ic_close_24,
            textColor = White,
            tintColor = White,
            modifier = Modifier.fillMaxWidth(),
            onAction = {}
        )
        BottomSheetItem(
            actionTextId = R.string.bottom_sheet_declare,
            actionDrawableId = R.drawable.ic_declare_fill_24,
            textColor = Red,
            tintColor = Red,
            modifier = Modifier.fillMaxWidth(),
            onAction = {}
        )
    }
}

@Composable
fun BottomSheetItem(
    @StringRes actionTextId: Int,
    @DrawableRes actionDrawableId: Int,
    textColor: Color,
    tintColor: Color,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .clickable {
                onAction()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier,
            painter = painterResource(id = actionDrawableId),
            tint = tintColor,
            contentDescription = ""
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = actionTextId),
            style = Body1,
            color = textColor,
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0XFFFFFFFF)
@Composable
fun BottomSheetPreview() {
    SsamDTheme {
        BottomSheetSample()
    }
}