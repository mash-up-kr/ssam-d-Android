package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.*
import com.mashup.presentation.R

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = Gray01,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        elevation = BottomNavigationDefaults.Elevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Composable
fun RowScope.KeyLinkNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    alwaysShowLabel: Boolean = true,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        label = label,
        enabled = enabled,
        modifier = modifier,
        alwaysShowLabel = alwaysShowLabel,
        selectedContentColor = White,
        unselectedContentColor = Gray04,
    )
}

@Preview(showBackground = true)
@Composable
fun KeyLinkNavigationBarPreview() {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val items = listOf("홈", "시그널 보내기", "채팅")
    val icons = listOf(
        painterResource(id = R.drawable.ic_home_fill_32),
        painterResource(id = R.drawable.ic_home_fill_32),
        painterResource(id = R.drawable.ic_chat_fill_32)
    )

    val selectedIcons = listOf(
        painterResource(id = R.drawable.ic_home_fill_32),
        painterResource(id = R.drawable.ic_home_fill_32),
        painterResource(id = R.drawable.ic_chat_fill_32)
    )
    SsamDTheme {
        Surface(color = Black) {
            KeyLinkNavigationBar {
                items.forEachIndexed { index, item ->
                    KeyLinkNavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                painter = icons[index],
                                contentDescription = ""
                            )
                        },
                        selectedIcon = {
                            Icon(
                                painter = selectedIcons[index],
                                contentDescription = ""
                            )
                        },
                        label = {
                            Text(text = item)
                        }
                    )
                }
            }
        }
    }
}