package com.mashup.presentation.signal.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mashup.presentation.ui.common.ButtonHeight
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import com.mashup.presentation.R

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */
@Composable
fun NotifyContentLengthDialog(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { }
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = Gray02
        ) {
            NotifyDialogContent(
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun NotifyDialogContent(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.dialog_signal_content),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            style = TextStyle(
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        KeyLinkButton(
            text = stringResource(id = R.string.ok),
            onClick = onButtonClick,
            buttonHeight = ButtonHeight.Medium,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            enable = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotifyDialogContentPreview() {
    SsamDTheme {
        NotifyContentLengthDialog()
    }
}