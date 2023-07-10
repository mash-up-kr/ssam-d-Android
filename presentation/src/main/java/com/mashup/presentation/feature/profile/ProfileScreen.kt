package com.mashup.presentation.feature.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier
) {

    ProfileScreen(
        modifier = modifier
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = { KeyLinkToolbar() }
    ) { innerPaddingValues ->
        ProfileContent(modifier = Modifier.padding(innerPaddingValues))
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
) {


}
