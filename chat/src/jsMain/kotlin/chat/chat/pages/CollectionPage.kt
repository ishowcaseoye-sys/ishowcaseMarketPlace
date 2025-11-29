package chat.chat.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import chat.chat.components.widgets.AssetSvgButton
import chat.chat.data.CollectionsDataProvider
import chat.chat.models.CollectionPageData
import chat.chat.utils.SpacedColumn
import chat.chat.utils.SpacedRow
import chat.chat.utils.Styles
import chat.chat.utils.TextBox
import chat.chat.utils.Wrap

@Composable
fun CollectionPage(collectionId: String) {
    val collectionsDataProvider = remember { CollectionsDataProvider() }
    val data: CollectionPageData = remember(collectionsDataProvider) {
        collectionsDataProvider.getVideosForCollectionId(collectionId)
    }
    SpacedColumn(spacePx = 39, modifier = Modifier.fillMaxWidth()) {
        Wrap(modifier = Modifier.fillMaxWidth(), horizontalGapPx = 16) {
            SpacedRow(spacePx = 4, modifier = Modifier.weight(1)) {
                TextBox(text = "Collection:", size = 20, color = Styles.WHITE.copyf(alpha = 0.5f))
                TextBox(text = data.name, size = 20)
            }
            AssetSvgButton(
                id = "manage_collection_button",
                isDense = true,
                onClick = {},
                text = "Manage Collection",
            )
        }
        //MainVideosGrid(videos = data.videos, minWidth = 356.px)
    }


}
