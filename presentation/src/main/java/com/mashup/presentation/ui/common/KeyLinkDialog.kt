package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.*


/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/22
 */
@Stable
private val KeyLinkDialogPadding = PaddingValues(
    start = 20.dp,
    end = 20.dp,
    top = 24.dp,
    bottom = 12.dp
)

@Composable
fun KeyLinkDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = properties
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = Gray02
        ) {
            content()
        }
    }
}

@Composable
fun KeyLinkContentLengthDialog(
    onDismissRequest: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    KeyLinkDialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier.padding(KeyLinkDialogPadding)
        ) {
            Text(
                text = stringResource(id = R.string.dialog_signal_content_title),
                modifier = Modifier.fillMaxWidth(),
                style = Heading4,
                color = White
            )

            Spacer(modifier = Modifier.height(36.dp))

            KeyLinkButton(
                text = stringResource(id = R.string.ok),
                buttonHeight = ButtonHeight.Medium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                enable = true,
                onClick = { onButtonClick() }
            )
        }
    }
}

@Composable
fun KeyLinkGoBackDialog(
    onDismissRequest: () -> Unit = {},
    onGoBackClick: () -> Unit = {},
    onCloseClick: () -> Unit = {}
) {
    KeyLinkDialog(onDismissRequest = { onDismissRequest() }) {
        KeyLinkTwoButtonContent(
            titleText = stringResource(id = R.string.dialog_go_back_title),
            subtitleText = stringResource(id = R.string.dialog_go_back_subtitle),
            positiveText = stringResource(id = R.string.dialog_go_back),
            negativeText = stringResource(id = R.string.close),
            positiveButtonColor = Red,
            onPositiveClick = { onGoBackClick() },
            onNegativeClick = { onCloseClick() }
        )
    }
}

@Composable
fun KeyLinkDisconnectSignalDialog(
    onDismissRequest: () -> Unit = {},
    onDisconnectClick: () -> Unit = {},
    onCloseClick: () -> Unit = {}
) {
    KeyLinkDialog(onDismissRequest = { onDismissRequest() }) {
        KeyLinkTwoButtonContent(
            titleText = stringResource(id = R.string.dialog_disconnect_signal_title),
            subtitleText = stringResource(id = R.string.dialog_disconnect_signal_subtitle),
            positiveText = stringResource(id = R.string.dialog_disconnect_signal),
            negativeText = stringResource(id = R.string.close),
            positiveButtonColor = Red,
            onPositiveClick = { onDisconnectClick() },
            onNegativeClick = { onCloseClick() }
        )
    }
}

@Composable
fun KeyLinkGoFirstDialog(
    onDismissRequest: () -> Unit = {},
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = {}
) {
    KeyLinkDialog(onDismissRequest = { onDismissRequest() }) {
        KeyLinkTwoButtonContent(
            titleText = stringResource(id = R.string.dialog_go_first_title),
            subtitleText = stringResource(id = R.string.dialog_go_first_subtitle),
            positiveText = stringResource(id = R.string.dialog_go_first_yes),
            negativeText = stringResource(id = R.string.dialog_go_first_no),
            positiveButtonColor = Red,
            onPositiveClick = { onPositiveClick() },
            onNegativeClick = { onNegativeClick() }
        )
    }
}

@Composable
fun KeyLinkLogoutDialog(
    onDismissRequest: () -> Unit = {},
    onCloseClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    KeyLinkDialog(onDismissRequest = { onDismissRequest() }) {
        KeyLinkTwoButtonContent(
            titleText = stringResource(id = R.string.dialog_logout_title),
            subtitleText = stringResource(id = R.string.dialog_logout_subtitle),
            positiveText = stringResource(id = R.string.dialog_logout),
            negativeText = stringResource(id = R.string.close),
            positiveButtonColor = Blurple,
            onPositiveClick = { onLogoutClick() },
            onNegativeClick = { onCloseClick() }
        )
    }
}

@Composable
private fun KeyLinkTwoButtonContent(
    titleText: String,
    subtitleText: String,
    positiveText: String,
    negativeText: String,
    positiveButtonColor: Color,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(KeyLinkDialogPadding)
    ) {
        Text(
            text = titleText,
            modifier = Modifier.fillMaxWidth(),
            style = Heading4,
            color = White
        )

        Text(
            text = subtitleText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            style = Body2,
            color = Gray06
        )

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KeyLinkButton(
                modifier = Modifier.weight(1f),
                text = negativeText,
                buttonHeight = ButtonHeight.Medium,
                fontWeight = FontWeight.SemiBold,
                backgroundColor = Gray03,
                contentColor = Gray06,
                enable = true,
                onClick = { onNegativeClick() }
            )
            KeyLinkButton(
                modifier = Modifier.weight(1f),
                text = positiveText,
                buttonHeight = ButtonHeight.Medium,
                backgroundColor = positiveButtonColor,
                fontWeight = FontWeight.SemiBold,
                enable = true,
                onClick = { onPositiveClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkContentLengthDialogPreview() {
    SsamDTheme {
        KeyLinkContentLengthDialog()
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkGoBackDialogPreview() {
    SsamDTheme {
        KeyLinkGoBackDialog()
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkDisconnectSignalDialogPreview() {
    SsamDTheme {
        KeyLinkDisconnectSignalDialog()
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkGoFirstDialogPreview() {
    SsamDTheme {
        KeyLinkGoFirstDialog()
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkLogoutDialogPreview() {
    SsamDTheme {
        KeyLinkLogoutDialog()
    }
}