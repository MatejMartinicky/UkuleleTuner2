/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
/**
 * Landscape layout for sign-in screen with side-by-side image and content
 *
 * @param onSignInClick callback function triggered when sign-in is attempted
 */
@Composable
internal fun LandscapeSignInLayout(onSignInClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        SignInBackgroundImage(
            painter = painterResource(id = R.drawable.sing_in_landscape),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        SignInContent(
            logoSize = 120.dp,
            onSignInClick = onSignInClick,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
        )
    }
}