package chat.core.components.widgets

import androidx.compose.runtime.Composable
import chat.core.G
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.skia.FontWeight

val TextButtonStyle = CssStyle.base {
    Modifier
        .width(G.Ui.Width.Medium)
        .margin(10.px)
        .padding(4.px)
        .fontSize(G.Ui.Text.MediumSmall)
        .fontWeight(FontWeight.NORMAL)
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = { onClick() }, TextButtonStyle.toModifier().then(modifier), enabled = enabled) {
        Text(text)
    }
}
