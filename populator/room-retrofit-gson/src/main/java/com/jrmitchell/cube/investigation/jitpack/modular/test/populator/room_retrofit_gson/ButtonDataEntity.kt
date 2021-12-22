package com.jrmitchell.cube.investigation.jitpack.modular.test.populator.room_retrofit_gson

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData

@Entity
/**
 * Entity wrapping the data for a button
 *
 * @param pageUri the URI of the page that the button data is associated with
 * @param buttonText the text of the button
 * @param actionId the action ID of the button
 */
data class ButtonDataEntity(
	@ColumnInfo(name = "page_uri") val pageUri: String,
	@ColumnInfo(name = "button_text") val buttonText: String,
	@ColumnInfo(name = "action_id") val actionId: String
) {
	@PrimaryKey(autoGenerate = true) var id: Int = 0
	constructor(pageUri: String, buttonData: ButtonData) : this(pageUri, buttonData.buttonText, buttonData.buttonAction.actionId)
	
	/**
	 * Get an instance of [ButtonData] associated with this
	 */
	fun toButtonData() = ButtonData(buttonText, ActionData(actionId))
}