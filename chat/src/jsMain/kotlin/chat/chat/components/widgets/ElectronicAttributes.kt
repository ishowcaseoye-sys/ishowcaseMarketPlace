package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import chat.chat.data.electronic.ELECTRONIC_SUBTYPE
import chat.chat.data.electronic.ELECTRONIC_TYPE
import chat.chat.data.electronic.ELECTRONIC_USE
import chat.chat.data.electronic.Electronic
import chat.chat.utils.Asset
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import org.jetbrains.compose.web.css.px

@Composable
fun electronicAttr (electronic:Electronic) {

    Column() {

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Electronic Type : "+ELECTRONIC_TYPE[electronic.electTypeId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Electronic Use : "+ELECTRONIC_USE[electronic.electUseId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox(text = "Subtype : "+ELECTRONIC_SUBTYPE [electronic.electSubtypeId ],
                modifier = Modifier.padding(top=8.px))
        }

        Box(modifier = Modifier.height(20.5.px))

    }
}