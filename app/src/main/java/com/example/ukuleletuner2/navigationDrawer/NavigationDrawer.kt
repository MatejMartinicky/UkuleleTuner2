/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=JLICaBEiJS0
 */

package com.example.ukuleletuner2.navigationDrawer

/**
 * Navigation Drawer is split to two parts (as in tutorial)
 */
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.R

/**
 * Header part displays only head of navigation drawer
 */
@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.drawer_name),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

/**
 * Body part displays body of navigation drawer
 *
 * @param items list of items to be displayed
 * @param modifier modifier that is applied
 * @param itemTextStyle style of text for items
 * @param onItemClick what happens when item is clicked (where to navigate)
 */
@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = item.title,
                    style = itemTextStyle
                )
            }
        }
    }
}