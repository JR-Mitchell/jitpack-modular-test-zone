package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.images.picasso.PicassoImageLoader

enum class ImageLoaderType(val activityGetter: ActivityActivity.() -> AndroidImageLoader, val fragmentGetter: FragmentActivity.() -> AndroidImageLoader) {
	PICASSO(
		{ PicassoImageLoader },
		{ PicassoImageLoader },
	),
	PLACEHOLDER(
		{ PlaceholderImageLoader() },
		{ PlaceholderImageLoader() }
	);
	
	companion object {
		fun valueOfOrDefault(string: String?) = try {
			valueOf(string!!)
		}
		catch (e: Throwable) {
			PICASSO
		}
	}
}