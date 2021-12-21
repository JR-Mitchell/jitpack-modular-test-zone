package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.LoadingIndicator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

class PlaceholderDisplayPopulator : DisplayPopulator {
	companion object {
		private const val prefix = "screen"
	}
	private fun actionName(suffix: String) = prefix + suffix
	private fun redirectAction(suffix: String) = ActionData(actionName(suffix))
	
	private val defaultData = DisplayData(
		ImageData(40, 40, "https://avatars.githubusercontent.com/u/8377714?v=4"),
		"android figure",
		"Main Title",
		listOf(
			ButtonData("Next", redirectAction("First")),
			ButtonData("Finish", ActionData("close"))
		)
	)
	
	private val data = hashMapOf<String?, DisplayData>(
		actionName("First") to DisplayData(
			ImageData(40, 40, "https://github.com/JR-Mitchell/DriveFinanceAndroid/blob/main/app/src/main/ic_launcher-playstore.png?raw=true"),
			"android figure",
			"First page",
			listOf(
				ButtonData("Option A", redirectAction("OptionA")),
				ButtonData("Option B", redirectAction("OptionB")),
				ButtonData("Option C", redirectAction("OptionC")),
				ButtonData("Back", ActionData("back"))
			)
		),
		actionName("OptionA") to DisplayData(
			ImageData(40, 40, "https://avatars.githubusercontent.com/u/8377714?v=4"),
			"android figure",
			"You selected Option A",
			listOf(
				ButtonData("Confirm", redirectAction("Confirm")),
				ButtonData("Back", ActionData("back")),
				ButtonData("Close", ActionData("close"))
			)
		),
		actionName("OptionB") to DisplayData(
			ImageData(40, 40, "https://avatars.githubusercontent.com/u/8377714?v=4"),
			"android figure",
			"You selected Option B",
			listOf(
				ButtonData("Confirm", redirectAction("Confirm")),
				ButtonData("Back", ActionData("back")),
				ButtonData("Close", ActionData("close"))
			)
		),
		actionName("OptionC") to DisplayData(
			ImageData(40, 40, "https://avatars.githubusercontent.com/u/8377714?v=4"),
			"android figure",
			"You selected Option C",
			listOf(
				ButtonData("Confirm", redirectAction("Confirm")),
				ButtonData("Back", ActionData("back")),
				ButtonData("Close", ActionData("close"))
			)
		),
		actionName("Confirm") to DisplayData(
			ImageData(40, 40, "https://github.com/JR-Mitchell/DriveFinanceAndroid/blob/main/app/src/main/ic_launcher-playstore.png?raw=true"),
			"android figure",
			"You confirmed",
			listOf(
				ButtonData("Back to start", redirectAction("Z")),
				ButtonData("Close", ActionData("close"))
			)
		),
	)
	
	override fun populateDisplayFromUri(pageUri: String?, target: DisplayTarget, loadingIndicator: LoadingIndicator) {
		loadingIndicator.setLoadingState(true)
		target.displayData(data[pageUri] ?: defaultData)
		loadingIndicator.setLoadingState(false)
	}
}