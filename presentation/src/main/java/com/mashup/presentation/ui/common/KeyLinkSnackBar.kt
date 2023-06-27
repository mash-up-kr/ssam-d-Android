package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.BlackAlpha50
import com.mashup.presentation.ui.theme.Caption2
import com.mashup.presentation.ui.theme.Mint
import com.mashup.presentation.ui.theme.White


/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@Composable
fun KeyLinkSnackBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = modifier,
    ) {
        snackBarHostState.currentSnackbarData?.let{ snackBarData ->
            KeyLinkSnackBarContent(snackBarData.message)
        }
    }
}

@Composable
fun KeyLinkSnackBarContent(
    snackBarMessage: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(36.dp),
        backgroundColor = BlackAlpha50,
        modifier = modifier
            .wrapContentWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_square_check_mint_24),
                tint = Mint,
                contentDescription = stringResource(R.string.content_description_snackbar_icon),
            )

            Text(
                text = snackBarMessage,
                style = Caption2,
                color = White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}