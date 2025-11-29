package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.thenIf
import chat.chat.utils.MouseEventState
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.clickable
import org.w3c.dom.HTMLElement

@Composable
fun UnderlinedToggleText(
    isSelected: Boolean,
    mouseEventState: MouseEventState,
    activeText: String = "Show Less",
    inactiveText: String = "Show More",
    ref: ElementRefScope<HTMLElement>? = null,
    onClick: () -> Unit,
) {
    TextBox(
        ref = ref,
        modifier = Modifier
            .clickable(onClick = onClick)
            .thenIf(mouseEventState == MouseEventState.Hovered) {
                Modifier.textDecorationLine(TextDecorationLine.Underline)
            },
        color = Styles.WHITE.copyf(alpha = 0.47f),
        text = if (isSelected) activeText else inactiveText,
    )
}
