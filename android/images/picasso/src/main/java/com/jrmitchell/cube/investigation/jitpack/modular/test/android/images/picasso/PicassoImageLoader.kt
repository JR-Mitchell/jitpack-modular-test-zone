package com.jrmitchell.cube.investigation.jitpack.modular.test.android.images.picasso

import android.widget.ImageView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ImageData
import com.squareup.picasso.Picasso

object PicassoImageLoader : AndroidImageLoader {
	override fun loadImage(image: ImageData, view: ImageView) {
		Picasso.get().load(image.uri).into(view)
	}
}