package com.mashup.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R

@Composable
fun LoginScreen() {
    Box {
        Background()
        
        Scaffold(
            backgroundColor = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    KeylinkTitle()

                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.img_plant),
                        contentDescription = null
                    )
                }

                Image(
                    modifier = Modifier
                        .padding(bottom = 118.dp, start = 20.dp, end = 20.dp)
                        .height(52.dp)
                        .width(312.dp)
                        .clickable {},
                    painter = painterResource(R.drawable.img_kakao_login),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun Background() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.img_space),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun KeylinkTitle() {
    Column(
        modifier = Modifier
            .padding(top = 120.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .height(61.dp)
                .width(209.dp),
            painter = painterResource(R.drawable.img_keylink),
            contentDescription = null
        )

        Text(text = "나의 키워드 행성을 찾아서")
    }
}
