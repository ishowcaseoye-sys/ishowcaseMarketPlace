package chat.site

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import chat.chat.data.repository.DataRepository
import chat.chat.data.repository.DataRepositoryImpl


import chat.chat.utils.Styles
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent

@InitSilk
fun overrideRootHTMLStyle(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("html") {
        // TODO: Remove scrollBehavior modifier from throughout the codebase & verify as well
        Modifier.background(Styles.SURFACE).scrollBehavior(ScrollBehavior.Smooth)
    }
}

@InitSilk
fun initializeBreakpoints(ctx: InitSilkContext) {
    ctx.theme.breakpoints = BreakpointSizes(
        sm = 576.px,
        md = 768.px,
        lg = 992.px,
        xl = 1200.px,
    )
}

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    console.info("AppEntry Begin")

    LaunchedEffect(Unit) {
        val dataRepository: DataRepository = DataRepositoryImpl()
        //dataRepository.loadProperty()
    }

    LaunchedEffect(Unit) {
        // Disable drag/drop globally
        document.apply {
            addEventListener("dragstart", { event -> event.preventDefault() })
            addEventListener("drop", { event -> event.preventDefault() })
            addEventListener("dragover", { event -> event.preventDefault() })
        }

        // Disable space bar causing page to scroll
        window.addEventListener("keydown", { event ->
            if ((event as KeyboardEvent).keyCode == 32 && event.target !is HTMLInputElement) {
                event.preventDefault()
            }
        })
    }

    SilkApp {
        Surface(
            modifier = Modifier
                .minHeight(100.vh)
                .fontFamily("-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
                    "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif")
                .background(Styles.SURFACE),
            colorModeOverride = ColorMode.DARK,
        ) {
            content()
        }
    }
}
