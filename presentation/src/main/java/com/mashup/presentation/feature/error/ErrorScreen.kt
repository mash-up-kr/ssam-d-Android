package com.mashup.presentation.feature.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.Gray10
import com.mashup.presentation.ui.theme.SsamDTheme

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
@Composable
fun NetworkErrorScreen(
    onBackClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = onBackClick
            )
        }
    ) { paddingValues ->
        val innerPadding =
            PaddingValues(top = paddingValues.calculateTopPadding(), start = 20.dp, end = 20.dp)

        ErrorContent(
            modifier = Modifier.padding(innerPadding),
            onButtonClick = onButtonClick
        )
    }
}

@Composable
private fun ErrorContent(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(R.string.content_description_error),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.network_error_title),
            style = Body2,
            color = Gray10,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(62.dp))

        KeyLinkButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.button_go_back),
            onClick = onButtonClick,
            enable = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    SsamDTheme {
        NetworkErrorScreen(
            onBackClick = {},
            onButtonClick = {}
        )
    }
}