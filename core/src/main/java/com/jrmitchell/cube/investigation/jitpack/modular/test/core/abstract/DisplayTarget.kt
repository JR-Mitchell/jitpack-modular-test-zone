package com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract

import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.DisplayData

/**
 * Interface for a generic target where [DisplayData] can be displayed
 */
interface DisplayTarget {
	/**
	 * Display some [DisplayData] in this [DisplayTarget]
	 *
	 * @param data the data to display
	 */
	fun displayData(data : DisplayData)
	
	/**
	 * Display an error thrown during attempting to populate this [DisplayTarget]
	 */
	fun displayError(throwable : Throwable? = null)
}