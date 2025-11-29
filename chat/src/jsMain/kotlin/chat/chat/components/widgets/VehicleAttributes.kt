package chat.chat.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import chat.chat.data.vehicle.VEHICLE_TYPE
import chat.chat.data.vehicle.VEHICLE_USE
import chat.chat.data.vehicle.VEH_POWER_SOURCE
import chat.chat.data.vehicle.Vehicle
import chat.chat.utils.Asset
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import org.jetbrains.compose.web.css.px

@Composable
fun vehicleAttr (vehicle:Vehicle) {

    Column() {

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Vehicle Type : "+VEHICLE_TYPE[vehicle.vehTypeId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Vehicle Use : "+VEHICLE_USE[vehicle.vehUseId],
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox(text = "Power source : "+VEH_POWER_SOURCE [vehicle.powerSource ],
                modifier = Modifier.padding(top=8.px))
        }

        Box(modifier = Modifier.height(10.5.px))
        Row() {
            AssetImageButton(
                asset = Asset.Icon.BEDROOM,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Odometer : "+vehicle.odometer,
                modifier = Modifier.padding(top=8.px))

        }
        Box(modifier = Modifier.height(10.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Number of Cylinders : "+vehicle.cylinders,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(10.5.px))

        Row() {
            AssetImageButton(
                asset = Asset.Icon.PARKING,
                containerColor = Styles.ELEVATED_BUTTON_CONTAINER,
            ) {}
            Box(modifier = Modifier.width(20.px))
            TextBox("Transmission : "+vehicle.transmission,
                modifier = Modifier.padding(top=8.px))
        }
        Box(modifier = Modifier.height(20.5.px))

    }
}