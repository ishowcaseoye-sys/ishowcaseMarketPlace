package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import chat.chat.utils.Constants
import chat.chat.utils.Styles
import chat.chat.utils.noShrink
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.width
import org.jetbrains.compose.web.css.px

// TODO: Use this divider throughout the codebase
@Composable
fun VerticalDivider(modifier: Modifier = Modifier) {
    Box(
        Modifier.background(Styles.DIVIDER_LIGHTER)
            .height(Constants.VERTICAL_DIVIDER_SIZE.height.px)
            .width(Constants.VERTICAL_DIVIDER_SIZE.width.px)
            .noShrink()
            .then(modifier)
    )
}
