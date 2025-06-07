package com.example.ukuleletuner2
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.ui.components.cards.LanguageSelector
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel

import com.example.ukuleletuner2.ui.components.cards.ThemeCard
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    themeViewModel: ThemeViewModel,
    settingsViewModel: SettingsViewModel
) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        settingsViewModel.initializeLanguage(context)
    }

    when(windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(R.string.settings_screen_title))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        navigationIcon = {
                            IconButton(onClick = { settingsViewModel.onMenuClick() }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = stringResource(R.string.menu_content_description),
                                )
                            }
                        },
                        actions = { /*todo*/ }
                    )
                },
                content = { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        item {
                            Text(
                                text = stringResource(R.string.settings_general),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        item {
                            ThemeCard(themeViewModel = themeViewModel)
                        }

                        item {
                            LanguageSelector(settingsViewModel = settingsViewModel)
                        }

                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        item {
                            Text(
                                text = stringResource(R.string.settings_info),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        item {
                            Column {
                                Text(
                                    text = stringResource(R.string.author),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = stringResource(R.string.author_name),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Thin
                                )
                            }
                        }

                        item {
                            Column {
                                Text(
                                    text = stringResource(R.string.version),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = stringResource(R.string.version_version),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Thin
                                )
                            }
                        }

                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        item {
                            Text(
                                text = stringResource(R.string.contact),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        item {
                            Column {
                                Text(
                                    text = stringResource(R.string.email),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = stringResource(R.string.email_address),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Thin
                                )
                            }
                        }
                    }
                }
            )
        }

        is WindowOrientation.Landscape -> {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(R.string.settings_screen_title))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        navigationIcon = {
                            IconButton(onClick = { settingsViewModel.onMenuClick() }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = stringResource(R.string.menu_content_description),
                                )
                            }
                        },
                        actions = { /*todo*/ }
                    )
                },
                content = { paddingValues ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp)
                        ) {
                            item {
                                Text(
                                    text = stringResource(R.string.settings_general),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            item {
                                ThemeCard(themeViewModel = themeViewModel)
                            }

                            item {
                                LanguageSelector(settingsViewModel = settingsViewModel)
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp)
                        ) {
                            item {
                                Text(
                                    text = stringResource(R.string.settings_info),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            item {
                                Column {
                                    Text(
                                        text = stringResource(R.string.author),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = stringResource(R.string.author_name),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Thin
                                    )
                                }
                            }

                            item {
                                Column {
                                    Text(
                                        text = stringResource(R.string.version),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = stringResource(R.string.version_version),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Thin
                                    )
                                }
                            }

                            item {
                                Text(
                                    text = stringResource(R.string.contact),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            item {
                                Column {
                                    Text(
                                        text = stringResource(R.string.email),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = stringResource(R.string.email_address),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Thin
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}