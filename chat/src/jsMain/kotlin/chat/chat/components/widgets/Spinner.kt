package chat.chat.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import org.jetbrains.compose.web.css.*

val SpinnerSpin = Keyframes {
    from { Modifier.rotate(0.deg) }
    to { Modifier.rotate(360.deg) }
}

@Composable
fun Spinner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.px)
            .border(4.px, LineStyle.Solid, Colors.LightGray)
            .borderTop(4.px, LineStyle.Solid, Colors.Blue) // The spinning part
            .borderRadius(50.percent)
            .animation(
                SpinnerSpin.toAnimation(
                    duration = 1.s,
                    iterationCount = AnimationIterationCount.Infinite,
                    timingFunction = AnimationTimingFunction.Linear
                )
            )
    )
}

