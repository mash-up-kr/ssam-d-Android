package com.mashup.presentation.ui.common

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.White

@Composable
fun KeyLinkToolbar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    menuAction: @Composable () -> Unit = {},
    onClickBack: () -> Unit = {},
    backgroundColor: Color = Black,
) {
    val focusManager = LocalFocusManager.current

    TopAppBar(
        title = title,
        backgroundColor = backgroundColor,
        contentColor = White,
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_left_24),
                    contentDescription = "백 버튼"
                )
            }
        },
        actions = {
            menuAction()
        },
        modifier = modifier
            .height(52.dp)
            .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        elevation = 0.dp
    )
}