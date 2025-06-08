/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.WelcomeScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.R

/**
 * Welcome screen content with title text and navigation button
 *
 * @param onNavigateToLoginScreen callback to navigate to login/sign-in screen
 * @param modifier optional modifier for styling and layout
 */
@Composable
internal fun WelcomeContent(
    onNavigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = TextStyle(
                fontSize = 54.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        WelcomeButton(onClick = onNavigateToLoginScreen)
    }
}