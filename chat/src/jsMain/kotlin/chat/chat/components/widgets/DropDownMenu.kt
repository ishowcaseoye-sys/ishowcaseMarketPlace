package chat.chat.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.percent

import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun CustomDropdown(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .position(org.jetbrains.compose.web.css.Position.Relative)
    ) {
        //Dropdown Trigger (Button-like appearance)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.px)
                //.border(1.px, BorderStyle, Colors.Gray)
                .cursor(Cursor.Pointer)
                .onClick { expanded = !expanded }
        ) {
            Text(
                selectedItem.ifEmpty { "Select an option" }
            )
            // Add an arrow icon here if desired
        }

        // Dropdown Menu (List of items)
        if (expanded) {
            Column(
                modifier = Modifier
                    .position(org.jetbrains.compose.web.css.Position.Absolute)
                    .top(50.percent)
                    .left(0.px)
                    .right(0.px)
                    .backgroundColor(Colors.White)
                    //.border(1.px, org.jetbrains.compose.web.css.BorderStyle.Solid, Colors.Gray)
                    .zIndex(50) // Ensure dropdown is above other content
            ) {
                items.forEach { item ->
                    DropdownItem(
                        text = item,
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownItem(text: String, onClick: () -> Unit) {
    Text(
        text,
        /*modifier = Modifier
            .fillMaxWidth()
            .padding(10.px)
            .cursor(Cursor.Pointer)
            .onClick { onClick() }
            .onMouseEnter { Modifier.backgroundColor(Colors.Gray) }
            .onMouseLeave { Modifier.backgroundColor(Colors.White) },*/

    )
}