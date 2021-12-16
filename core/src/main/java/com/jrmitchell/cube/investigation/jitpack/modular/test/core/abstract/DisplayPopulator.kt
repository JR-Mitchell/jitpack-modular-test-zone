package com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract

/**
 * Interface for populating a [DisplayTarget] from page data
 */
interface DisplayPopulator {
	/**
	 * Populate a [DisplayTarget] with data fetched based on a given [pageUri]
	 *
	 * @param pageUri the URI to fetch data based on
	 * @param target the target to display the fetched data in
	 */
	fun populateDisplayFromUri(pageUri : String?, target: DisplayTarget)
}
