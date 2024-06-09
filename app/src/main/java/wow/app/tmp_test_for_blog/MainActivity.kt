package wow.app.tmp_test_for_blog

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import wow.app.tmp_test_for_blog.ui.theme.Tmp_test_for_blogTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ), navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        setContent {
            Tmp_test_for_blogTheme {
                val view = LocalView.current
                val isDarkTheme = isSystemInDarkTheme()
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as Activity).window
                        window.statusBarColor =
                            androidx.compose.ui.graphics.Color.Transparent.toArgb()
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                            !isDarkTheme
                    }
                }
                val sheetState = rememberPlayerSheetState()
                val selectedNavItem by remember {
                    mutableIntStateOf(1)
                }
                PlayerSheetScaffold(sheetState = sheetState, fullPlayerContent = {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        color = androidx.compose.ui.graphics.Color.Magenta
                    ) {}
                }, miniPlayerContent = {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        color = androidx.compose.ui.graphics.Color.Green
                    ) {}
                }, navigationSuiteItems = {
                    listOf(
                        1, 2, 3, 4, 5, 6
                    ).forEach { navItem ->
                        item(
                            selected = selectedNavItem == navItem, onClick = {
                                //Do nothing
                            },
                            icon = {
                                Icon(
                                    imageVector = when (navItem) {
                                        1 -> Icons.Default.Person
                                        2 -> Icons.Default.PlayArrow
                                        3 -> Icons.Default.Call
                                        4 -> Icons.Default.Search
                                        5 -> Icons.Default.Face
                                        else -> Icons.Default.Favorite
                                    }, contentDescription = null
                                )
                            },
                            interactionSource = MutableInteractionSource()
                        )
                    }
                }, content = {

                    val scrollBehavior =
                        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                    Scaffold(
                        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            LargeTopAppBar(
                                title = {
                                    Text(
                                        text = "Items",
                                    )
                                },
                                scrollBehavior = scrollBehavior
                            )
                        }
                    ) {
                        LazyColumn(
                            Modifier
                                .fillMaxSize()
                                .padding(it)) {
                            items(1000) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        imageVector = Icons.Default.Face, contentDescription = null
                                    )
                                    Text(text = "$it $it $it $it $it")
                                }
                                Spacer(modifier = Modifier.height(1.dp))
                            }
                        }
                    }
                })
            }
        }
    }
}