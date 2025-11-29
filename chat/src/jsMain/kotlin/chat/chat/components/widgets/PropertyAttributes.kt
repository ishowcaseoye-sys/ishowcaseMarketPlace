package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import chat.chat.data.property.Property
import chat.chat.utils.Asset
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import org.jetbrains.compose.web.css.px

@Composable
fun propertyAttr (property:Property) {

    Column() {

        Row() {
            AssetImageButton(
                asset = Asset.Icon.BEDROOM,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of Parking Spots : "+property.parking,
                modifier = Modifier.padding(top=8.px))

        }
        Box(modifier = Modifier.height(20.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of Bedroom : "+property.bedroom,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))


        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of bathrooms : "+property.bathroom,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of Toilets : "+property.toilets,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of Living Rooms : "+property.livingRoom,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))

    }

}