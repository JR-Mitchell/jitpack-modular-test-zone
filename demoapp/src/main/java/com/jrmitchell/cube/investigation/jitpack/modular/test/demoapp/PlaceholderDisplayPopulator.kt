package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

class PlaceholderDisplayPopulator : DisplayPopulator {
	
	companion object {
		private val defaultData = DisplayData(
			ImageData(40, 40, "stuff"),
			"android figure",
			"Main Title",
			listOf(
				ButtonData("Next", ActionData("activityFirst")),
				ButtonData("Finish", ActionData("close"))
			)
		)
		private val data = hashMapOf<String?, DisplayData>(
			"activityFirst" to DisplayData(
				ImageData(40, 40, "stuff"),
				"android figure",
				"First page",
				listOf(
					ButtonData("Option A", ActionData("activityOptionA")),
					ButtonData("Option B", ActionData("activityOptionB")),
					ButtonData("Option C", ActionData("activityOptionC")),
					ButtonData("Back", ActionData("back"))
				)
			),
			"activityOptionA" to DisplayData(
				ImageData(40, 40, "stuff"),
				"android figure",
				"You selected Option A",
				listOf(
					ButtonData("Confirm", ActionData("activityConfirm")),
					ButtonData("Back", ActionData("back")),
					ButtonData("Close", ActionData("close"))
				)
			),
			"activityOptionB" to DisplayData(
				ImageData(40, 40, "stuff"),
				"android figure",
				"You selected Option B",
				listOf(
					ButtonData("Confirm", ActionData("activityConfirm")),
					ButtonData("Back", ActionData("back")),
					ButtonData("Close", ActionData("close"))
				)
			),
			"activityOptionC" to DisplayData(
				ImageData(40, 40, "stuff"),
				"android figure",
				"You selected Option C",
				listOf(
					ButtonData("Confirm", ActionData("activityConfirm")),
					ButtonData("Back", ActionData("back")),
					ButtonData("Close", ActionData("close"))
				)
			),
			"activityConfirm" to DisplayData(
				ImageData(40, 40, "stuff"),
				"android figure",
				"You confirmed",
				listOf(
					ButtonData("Close", ActionData("close"))
				)
			),
		)
	}
	
	override fun populateDisplayFromUri(pageUri: String?, target: DisplayTarget) {
		target.displayData(data[pageUri] ?: defaultData)
	}
}