package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import chat.chat.data.property.PROPERTY_SUBTYPE
import chat.chat.data.property.PROPERTY_TYPE
import chat.chat.data.property.PROPERTY_USE
import chat.chat.data.property.Property
import chat.chat.utils.Styles

@Composable
fun propertyTypes (property:Property) {


    Row() {

        AssetSvgButton(
            //ref = ref { e -> elementRef = e },
            //modifier = modifier,
            id = "subscribe_button",
            text =   PROPERTY_TYPE[property.propTypeId].toString(),
            containerColor = Styles.RED_LIGHT,
            contentColor = Styles.WHITE,
            //startIconPath = startIconPath,
            onClick = {
            },
        )

        AssetSvgButton(
            //ref = ref { e -> elementRef = e },
            //modifier = modifier,
            id = "subscribe_button",
            text =   PROPERTY_SUBTYPE[property.propSubtypeId].toString(),
            containerColor = Styles.BLUE_BORDER,
            contentColor = Styles.WHITE,
            //startIconPath = startIconPath,
            onClick = {
            },
        )

        AssetSvgButton(
            //ref = ref { e -> elementRef = e },
            //modifier = modifier,
            id = "subscribe_button",
            text =   PROPERTY_USE[property.propUseId].toString(),
            containerColor = Styles.LINK_BLUE,
            contentColor = Styles.WHITE,
            //startIconPath = startIconPath,
            onClick = {
            },
        )

    }



}