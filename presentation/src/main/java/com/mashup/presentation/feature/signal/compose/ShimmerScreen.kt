package com.mashup.presentation.feature.signal.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.common.extension.shimmerEffect
import com.mashup.presentation.ui.theme.SsamDTheme

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */
@Composable
fun ShimmerScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
                .height(38.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
                .height(26.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .shimmerEffect()
        )
        Row(
            modifier = Modifier.padding(end = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
        }
        Row(
            modifier = Modifier.padding(end = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(38.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
                .height(26.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerScreenPreview() {
    SsamDTheme {
        ShimmerScreen()
    }
}