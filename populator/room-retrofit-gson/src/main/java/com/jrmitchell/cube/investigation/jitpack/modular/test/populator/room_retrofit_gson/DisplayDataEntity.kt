package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

@Entity
/**
 * Entity wrapping the data for a display
 *
 * @param pageUri the URI of the page that the display data is associated with
 * @param titleText the text of the page title
 * @param imageContentDescription the content description of the image
 * @param imageData the data for the displayed image
 */
data class DisplayDataEntity(
	@PrimaryKey @ColumnInfo(name = "page_uri") val pageUri: String,
	@ColumnInfo(name = "title_text") val titleText: String,
	@ColumnInfo(name = "image_content_description") val imageContentDescription: String,
	@Embedded val imageData: ImageData
) {
	constructor(pageUri: String, displayData: DisplayData) : this(pageUri, displayData.titleText, displayData.imageContentDescription, displayData.imageData)
	
	/**
	 * Get an instance of [DisplayData] associated with this
	 *
	 * @param buttonList the list of buttons that the created [DisplayData] should have
	 */
	fun toDisplayData(buttonList : List<ButtonData>) = DisplayData(imageData, imageContentDescription, titleText, buttonList)
}