package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun ParentBaseScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(BottomParentNavItem.HOME) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomParentNavItem.entries.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem == item,
                        onClick = { selectedItem = item },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(text = item.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFFFA07A),
                            unselectedIconColor = Color(0xFF484C52),
                            selectedTextColor = Color(0xFFFFA07A),
                            unselectedTextColor = Color(0xFF484C52),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        modifier = modifier
    ) { pv ->
        Box(modifier = Modifier.padding(pv))
        // Content
    }
}

@Preview
@Composable
private fun ParentBaseScreenPreview() {
    BAPPTheme {
        ParentBaseScreen()
    }
}
