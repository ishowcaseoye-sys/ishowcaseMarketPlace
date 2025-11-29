package chat.chat.components.widgets

import androidx.compose.runtime.Composable

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.silk.components.graphics.Image
import chat.chat.utils.SpacedRow
import chat.chat.utils.Styles
import chat.chat.utils.TextBox

@Composable
fun IconLabel(iconAsset: String, label: String, secondaryLabel: String? = null) {
    SpacedRow(4) {
        Image(modifier = Modifier.opacity(0.3f), src = iconAsset)
        TextBox(label)
        secondaryLabel?.let { text -> TextBox(text, color = Styles.WHITE.copyf(alpha = 0.7f)) }
    }
}
