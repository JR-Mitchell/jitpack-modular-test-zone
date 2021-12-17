package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData

/**
 * Interface for an Android target where data can be displayed
 */
interface AndroidDisplayTarget<R : AndroidActionResolver, L : AndroidImageLoader> : DisplayTarget {
	/**
	 * View to be used as loading UI
	 */
	val loadingUi: View
	
	/**
	 * View to display the data's main image in
	 */
	val imageView: ImageView
	
	/**
	 * View to display the data's title in
	 */
	val titleView: TextView
	
	/**
	 * View to display the data's top button in
	 */
	val topButtonView: TextView
	
	/**
	 * Resolver for clicks on the button
	 */
	val actionResolver: R
	
	/**
	 * Loader for the image
	 */
	val imageLoader: L
	
	override fun displayData(data: DisplayData) {
		setLoadingState(false)
		titleView.text = data.titleText
		populateButton(topButtonView, data.topButton)
		imageView.contentDescription = data.imageContentDescription
		imageLoader.loadImage(data.imageData, imageView)
	}
	
	override fun displayError(throwable: Throwable?) {
		setLoadingState(false)
	}
	
	override fun setLoadingState(isLoading: Boolean) {
		loadingUi.isVisible = isLoading
	}
	
	private fun populateButton(button: TextView, data: ButtonData) {
		button.text = data.buttonText
		button.setOnClickListener {
			actionResolver.resolveAction(it.context, data.buttonAction)
		}
	}
}