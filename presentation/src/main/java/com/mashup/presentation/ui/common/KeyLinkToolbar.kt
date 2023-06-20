package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.White

@Composable
fun KeyLinkToolbar(
    title: @Composable () -> Unit = {},
    onClickBack: () -> Unit
) {
    TopAppBar(
        title = title,
        backgroundColor = Black,
        contentColor = White,
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_left_24),
                    contentDescription = "백 버튼"
                )
            }
        },
        modifier = Modifier.height(52.dp)
    )
}