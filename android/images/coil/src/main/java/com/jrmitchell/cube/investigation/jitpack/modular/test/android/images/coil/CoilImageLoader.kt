package com.jrmitchell.cube.investigation.jitpack.modular.test.android.images.coil

import android.widget.ImageView
import coil.load
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

/**
 * Image loader implementation based on Coil
 */
object CoilImageLoader : AndroidImageLoader {
	override fun loadImage(image: ImageData, view: ImageView) {
		view.load(image.uri) {
			crossfade(true)
		}
	}
}