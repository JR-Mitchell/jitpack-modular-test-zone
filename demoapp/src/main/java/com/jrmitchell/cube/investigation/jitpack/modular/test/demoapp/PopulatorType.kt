package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import androidx.lifecycle.lifecycleScope
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.populator.retrofit_gson.RetrofitGsonPopulator

enum class PopulatorType(val activityGetter : ActivityActivity.() -> DisplayPopulator, val fragmentGetter : FragmentActivity.() -> DisplayPopulator) {
	HARDCODED(
		{PlaceholderDisplayPopulator()},
		{PlaceholderDisplayPopulator()}
	),
	RETRO_GSON_LOADED(
		{ RetrofitGsonPopulator("https://raw.githubusercontent.com/JR-Mitchell/jitpack-modular-test-zone/$CURRENT_BRANCH/demoapp/demodata/", { this.lifecycleScope }, ".json")},
		{ RetrofitGsonPopulator("https://raw.githubusercontent.com/JR-Mitchell/jitpack-modular-test-zone/$CURRENT_BRANCH/demoapp/demodata/", { this.lifecycleScope }, ".json")}
	);
	companion object {
		fun valueOfOrDefault(string: String?) = try {
			valueOf(string!!)
		} catch (e : Throwable) {
			HARDCODED
		}
		const val CURRENT_BRANCH = "main"
	}
}