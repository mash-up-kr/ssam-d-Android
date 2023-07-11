package com.mashup.presentation.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkSwitch
import com.mashup.presentation.ui.theme.*


/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
@Composable
fun UserInfoContent(
    userImageUrl: String,
    userName: String,
    userEmail: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 24.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        /* GlideImage로 수정 필요*/
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = ""
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(9f),
                    text = userName,
                    style = Heading4,
                    color = White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onEditClick()
                        },
                    painter = painterResource(id = R.drawable.ic_pen_24),
                    tint = White,
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = userEmail,
                style = Body2,
                color = Gray05,
            )
        }

    }
}

@Composable
fun NavigationContent(
    description: String,
    onNavigateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = Gray02)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(9f),
                text = description,
                style = Body1,
                color = White
            )

            Icon(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateClick() },
                painter = painterResource(id = R.drawable.ic_chevron_right_24),
                contentDescription = "",
                tint = White
            )
        }

        Divider(
            modifier = Modifier.offset(y = 16.dp),
            thickness = 1.dp, color = Gray03
        )
    }
}

@Composable
fun Header(
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Black)
            .padding(horizontal = 20.dp, vertical = 12.dp),
    ) {
        Text(
            text = description,
            style = Body2,
            color = Gray06
        )
    }
}

@Composable
fun NotificationContent(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    description: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(color = Gray02)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = description,
                style = Body1,
                color = White
            )

            KeyLinkSwitch(
                isChecked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }

        Divider(
            modifier = Modifier.offset(y = 16.dp),
            thickness = 1.dp, color = Gray03
        )
    }
}

@Composable
fun AppVersionContent(
    description: String,
    appVersion: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = Gray02)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = description,
                style = Body1,
                color = White
            )

            Text(
                text = appVersion,
                style = Body1,
                color = Gray09
            )
        }

        Divider(
            modifier = Modifier.offset(y = 16.dp),
            thickness = 1.dp, color = Gray03
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoContentPreview() {
    SsamDTheme {
        Surface(color = Black) {
            UserInfoContent(
                userImageUrl = "",
                userName = "",
                userEmail = "",
                onEditClick = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun NavigationContentPreview() {
    SsamDTheme {
        NavigationContent(
            description = "이용약관",
            onNavigateClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    SsamDTheme {
        Header(
            description = "환경설정"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationContentPreview() {
    var isChecked by remember { mutableStateOf(false) }
    SsamDTheme {
        NotificationContent(
            isChecked = isChecked,
            onCheckedChange = { isChecked = it },
            description = "알림 소식 받기",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppVersionContentPreview() {
    SsamDTheme {
        AppVersionContent(
            description = "버전",
            appVersion = "1.0.0"
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LogoutContentPreview() {
//    SsamDTheme {
//        LogoutContent()
//    }
//}
