package com.mashup.presentation.signal.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.SsamDTheme

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */
@Composable
fun SignalKeywordScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    isLoading: Boolean
) {
//    var isLoading by rememberSaveable { mutableStateOf(false) }

//    SideEffect {
//        isLoading = true
//    }

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(onClickBack = navigateUp)
        NameScreen(
            isLoading = isLoading,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .weight(1f),
            afterLoadingContent = {
                SignalKeyword(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .weight(1f)
                )
            }
        )
    }
}

@Composable
fun NameScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    afterLoadingContent: @Composable () -> Unit
) {
    if (isLoading) {
        ShimmerScreen(modifier = modifier)
    } else {
        afterLoadingContent()
    }
}

@Composable
fun SignalKeyword(
    modifier: Modifier = Modifier
) {
    Text(text = "adsfasd")
}

@Preview(showBackground = true)
@Composable
fun SignalKeywordScreenPreview() {
    SsamDTheme {
        SignalKeywordScreen(isLoading = true)
    }
}