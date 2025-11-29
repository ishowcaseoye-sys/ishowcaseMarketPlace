package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*

import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.px


@Composable
fun ImageSliderPage( images: List<String> ) {
    /*val images = listOf(
        "images/image1.jpg", // Replace with your image paths
        "images/image2.jpg",
        "images/image3.jpg"
    )*/
    var currentIndex by remember { mutableStateOf(0) }

    // Function to go to the next image
    fun nextImage() {
        currentIndex = (currentIndex + 1) % images.size
    }

    // Function to go to the previous image
    fun prevImage() {
        currentIndex = (currentIndex - 1 + images.size) % images.size
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.px),
        contentAlignment = Alignment.Center
    ) {
        // Image Display

        Image(src = images[currentIndex], modifier = Modifier.fillMaxWidth())
        /*Image(
            src = images[currentIndex],
            contentDescription = "Slider Image ${currentIndex + 1}",
            modifier = Modifier.width(400.px).height(300.px),
            description = TODO(),
            variant = TODO(),
            width = TODO(),
            height = TODO(),
            loading = TODO(),
            decoding = TODO(),
            fetchPriority = TODO(),
            ref = TODO()
        )*/

        // Navigation Controls
        Row(
            modifier = Modifier.fillMaxWidth().justifyContent(JustifyContent.SpaceBetween).align(Alignment.Center)
        ) {
            // Previous Button
            SpanText(
                text = "< Prev",
                modifier = Modifier
                    .cursor(Cursor.Pointer)
                    .color(Colors.Blue)
                    .onClick { prevImage() }
            )

            Spacer() // Pushes the buttons to the edges

            // Next Button
            SpanText(
                text = "Next >",
                modifier = Modifier
                    .cursor(Cursor.Pointer)
                    .color(Colors.Blue)
                    .onClick { nextImage() }
            )
        }
    }
}