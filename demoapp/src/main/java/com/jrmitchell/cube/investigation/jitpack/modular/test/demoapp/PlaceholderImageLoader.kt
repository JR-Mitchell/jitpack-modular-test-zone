package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.widget.ImageView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

class PlaceholderImageLoader : AndroidImageLoader {
	override fun loadImage(image: ImageData, view: ImageView) {
		view.setImageResource(R.drawable.ic_launcher_foreground)
	}
}