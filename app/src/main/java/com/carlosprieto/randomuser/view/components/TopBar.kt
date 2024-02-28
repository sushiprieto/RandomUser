package com.carlosprieto.randomuser.view.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    titleText: String,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    onBackPress: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        title = {
            Text(
                text = titleText,
                color = contentColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = contentColor)
            }
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More options", tint = contentColor)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
            }
        },
        elevation = 0.dp
    )
}


