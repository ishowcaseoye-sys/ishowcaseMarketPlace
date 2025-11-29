package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import chat.chat.data.furniture.FURNITURE_SUBTYPE
import chat.chat.data.furniture.FURNITURE_TYPE
import chat.chat.data.furniture.FURNITURE_USE
import chat.chat.data.furniture.Furniture
import chat.chat.utils.Asset
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import org.jetbrains.compose.web.css.px

@Composable
fun furnitureAttr (furniture: Furniture) {

    Column() {

        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Furniture Type : "+ FURNITURE_TYPE[furniture.furnTypeId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox(text = "Furniture Use : "+FURNITURE_USE [furniture.furnUseId ],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox(text = "Furniture Subtype : "+FURNITURE_SUBTYPE [furniture.furnSubtypeId ],
                modifier = Modifier.padding(top=8.px))
        }


        Box(modifier = Modifier.height(20.5.px))

    }
}