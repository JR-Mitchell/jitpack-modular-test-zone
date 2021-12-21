package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract

import android.view.View
import androidx.core.view.isVisible
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.LoadingIndicator

interface AndroidLoadingIndicator : LoadingIndicator {
	/**
	 * View to be used as loading UI
	 */
	val loadingUi: View
	
	override fun setLoadingState(isLoading: Boolean) {
		loadingUi.isVisible = isLoading
	}
}