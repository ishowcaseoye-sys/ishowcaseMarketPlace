package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import chat.chat.data.fashion.FASHION_SUBTYPE
import chat.chat.data.fashion.FASHION_USE
import chat.chat.data.fashion.Fashion
import chat.chat.utils.Asset
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import org.jetbrains.compose.web.css.px

@Composable
fun fashionAttr (fashion:Fashion) {

    Column() {

        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Product Use : "+FASHION_USE[fashion.fashionUseId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox(text = "Subtype : "+FASHION_SUBTYPE [fashion.fashionSubtypeId ],
                modifier = Modifier.padding(top=8.px))
        }

        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.BEDROOM,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Category : "+fashion.category,
                modifier = Modifier.padding(top=8.px))

        }
        Box(modifier = Modifier.height(10.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Brand : "+fashion.brand,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Size : "+fashion.size,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Gender : "+fashion.gender,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))

    }
}