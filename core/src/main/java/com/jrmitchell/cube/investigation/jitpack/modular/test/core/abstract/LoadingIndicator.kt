package com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract

/**
 * Interface for a generic indicator of loading state for data display
 */
interface LoadingIndicator {
	/**
	 * Set the loading state for displayed data
	 */
	fun setLoadingState(isLoading : Boolean)
}