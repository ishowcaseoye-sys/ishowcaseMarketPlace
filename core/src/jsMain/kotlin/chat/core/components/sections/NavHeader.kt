package chat.core.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.Router
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

val NavHeaderStyle = CssStyle.base(extraModifier = { SmoothColorStyle.toModifier() }) {
    Modifier
        .fillMaxWidth()
        .height(60.px)
        .padding(leftRight = 10.px, topBottom = 5.px)
        // Intentionally invert the header colors from the rest of the page
        .backgroundColor(colorMode.toPalette().background)
}

val TitleStyle = CssStyle.base {
    Modifier
        .fontSize(36.px)
        .fontWeight(FontWeight.Normal)
        // Intentionally invert the header colors from the rest of the page
        .color(colorMode.toPalette().background)
}

val TitleStyle1 = CssStyle.base {
    Modifier
        .fontSize(30.px)
        .fontWeight(FontWeight.Bold)
        // Intentionally invert the header colors from the rest of the page
        .color(colorMode.toPalette().background)
}

val NavButtonStyle = CssStyle.base {
    Modifier
        .margin(leftRight = 5.px)
        .padding(0.px)
        .size(40.px)
        .borderRadius(50.percent)
}

@Composable
private fun NavButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(onClick = { onClick() }, NavButtonStyle.toModifier(), content = { content() })
}

abstract class NavHeaderAction {
    @Composable
    abstract fun render()

    abstract fun onActionClicked(router: Router)
}

object ExtraNavHeaderAction {
    private val mutableActionState by lazy { mutableStateOf<NavHeaderAction?>(null) }

    var current: NavHeaderAction?
        get() = mutableActionState.value
        set(value) {
            mutableActionState.value = value
        }
}

@Composable
fun NavHeader() {
    val ctx = rememberPageContext()
    //var colorMode by ColorMode.DARK
    Box(NavHeaderStyle.toModifier()) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
           /* Image(
                modifier = Modifier
                    .height(110.px)
                    .padding(topBottom = 4.px),
                    //.clickable(onClick = onLogoClick),
                src = Asset.Icon.LOGO_SHOWCASE,
            )*/
            // NavButton(onClick = { ctx.router.navigateTo("/") }) { FaHouse() }
            // NavButton(onClick = { ctx.router.navigateTo("/about") }) { FaQuestion() }
            Spacer()

            val router = rememberPageContext().router
            ExtraNavHeaderAction.current?.let { extraAction ->
                NavButton(onClick = { extraAction.onActionClicked(router) }) {
                    extraAction.render()
                }
            }

           /* NavButton(onClick = { colorMode = colorMode }) {
                when (colorMode) {
                    ColorMode.LIGHT -> FaMoon()
                    ColorMode.DARK -> FaSun()
                }
            }*/
        }

        /*
        Box(TitleStyle.toModifier().align(Alignment.Center)) {
            Text("Online Trading Platform \uD83D\uDCAC")
        }

         */
    }
}
