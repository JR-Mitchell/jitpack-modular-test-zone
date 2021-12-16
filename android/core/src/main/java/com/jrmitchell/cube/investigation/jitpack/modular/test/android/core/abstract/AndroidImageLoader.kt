package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract

import android.widget.ImageView
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData

/**
 * Interface for loading images into Android views
 */
interface AndroidImageLoader {
	/**
	 * Load an image specified by its [ImageData] into an [ImageView]
	 *
	 * @param image the data to load
	 * @param view the view to load the data into
	 */
	fun loadImage(image: ImageData, view: ImageView)
}