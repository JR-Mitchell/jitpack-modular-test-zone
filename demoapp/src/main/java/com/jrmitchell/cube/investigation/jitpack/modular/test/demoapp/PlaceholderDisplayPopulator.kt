package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

class PlaceholderDisplayPopulator : DisplayPopulator {
	
	companion object {
		private val data = DisplayData(
			ImageData(40, 40, "stuff"),
			"android figure",
			"Main Title",
			"Next",
			ActionData("activity")
		)
	}
	
	override fun populateDisplayFromUri(pageUri: String?, target: DisplayTarget) {
		target.displayData(data)
	}
}