package ru.disspear574.notus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.disspear574.notus.R
import ru.disspear574.notus.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    titleText: String,
    isFloatingIcon: Boolean? = false,
    floatingIcon: @Composable (() -> Unit)? = {},
    onFloatingButtonClick: (() -> Unit)? = {},
    action: @Composable (RowScope) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    val navigator = LocalNavigator.currentOrThrow
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            if (isFloatingIcon == true) {
                if (onFloatingButtonClick != null && floatingIcon != null) {
                    FloatingActionButton(
                        onClick = onFloatingButtonClick,
                        containerColor = Pink,
                        contentColor = Color.White,
                        content = floatingIcon
                    )
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = titleText, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Pink),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    if (navigator.canPop) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .clickable { navigator.pop() })
                    }
                },
                actions = action
            )
        },
        content = content
    )
}