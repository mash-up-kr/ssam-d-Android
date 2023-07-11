package com.mashup.presentation.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
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

//@Preview(showBackground = true)
//@Composable
//fun NavigationContentPreview() {
//    SsamDTheme {
//        NavigationContent()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HeaderPreview() {
//    SsamDTheme {
//        Header()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun NotificationContentPreview() {
//    SsamDTheme {
//        NotificationContent()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AppVersionContentPreview() {
//    SsamDTheme {
//        AppVersionContent()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LogoutContentPreview() {
//    SsamDTheme {
//        LogoutContent()
//    }
//}
