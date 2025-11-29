package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import chat.chat.utils.Styles
import chat.chat.utils.noShrink
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import org.jetbrains.compose.web.css.px

@Composable
fun HorizontalDivider(color: Color = Styles.DIVIDER_LIGHTER) {
    Box(Modifier.background(color).fillMaxWidth().height(1.px).noShrink())
}
