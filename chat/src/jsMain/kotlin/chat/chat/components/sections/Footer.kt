package chat.chat.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import chat.chat.utils.Styles
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Span

val FooterStyle = CssStyle.base {
    Modifier.backgroundColor( Styles.MISSED_VIDEOS_CONTAINER)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {

    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
            //val sitePalette = colorMode.toPalette()

            SpanText("Powered by ")

            // Huge thanks to UI Rocket (https://ui-rocket.com) for putting this great template design together for us!
            // If you like what you see here and want help building your own site, consider checking out their services.
            Link(
                "http://isoftfusion.com",
                "iSoft Fusion",
                Modifier.setVariable(ColorVar, Styles.RED).whiteSpace(WhiteSpace.NoWrap),
                variant = UncoloredLinkVariant
            )
        }
    }

}
