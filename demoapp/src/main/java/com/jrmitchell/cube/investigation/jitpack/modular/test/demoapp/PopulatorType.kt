package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator

enum class PopulatorType(val activityGetter : ActivityActivity.() -> DisplayPopulator, val fragmentGetter : FragmentActivity.() -> DisplayPopulator) {
	HARDCODED(
		{PlaceholderDisplayPopulator("activity")},
		{PlaceholderDisplayPopulator("fragment")}
	);
	companion object {
		fun valueOfOrDefault(string: String?) = try {
			valueOf(string!!)
		} catch (e : Throwable) {
			HARDCODED
		}
	}
}