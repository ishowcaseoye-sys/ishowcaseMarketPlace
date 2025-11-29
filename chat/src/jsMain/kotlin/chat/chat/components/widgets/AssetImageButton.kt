package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import chat.chat.utils.animatedColor
import chat.chat.utils.clickable
import chat.chat.utils.rememberMouseEventAsState
import chat.chat.utils.toKobwebColor
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLImageElement
import com.varabyte.kobweb.compose.ui.graphics.Color.Rgb as KobwebColor

@Composable
fun AssetImageButton(
    asset: String,
    modifier: Modifier = Modifier,
    onRefAvailable: ((HTMLImageElement) -> Unit)? = null,
    containerColor: KobwebColor = Colors.Transparent,
    onClick: (() -> Unit)? = null,
) {
    var buttonRef by remember { mutableStateOf<HTMLImageElement?>(null) }
    val animatedBackgroundColor by rememberMouseEventAsState(buttonRef).animatedColor(initialColor = containerColor)

    Image(
        ref = ref { e ->
            buttonRef = e
            onRefAvailable?.invoke(e)
        },
        modifier = Modifier
            .thenIf(onClick != null) { Modifier.background(animatedBackgroundColor.toKobwebColor()) }
            .borderRadius(100.percent)
            .clickable(onClick = onClick)
            .clip(Circle())
            .padding(8.px)
            .then(modifier),
        src = asset,
    )
}
