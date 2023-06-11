package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.White

@Composable
fun KeyLinkToolbar(
    onClickBack: () -> Unit
) {
    TopAppBar(
        title = { },
        backgroundColor = Black,
        contentColor = White,
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "백 버튼")
            }
        },
        modifier = Modifier.height(52.dp)
    )
}