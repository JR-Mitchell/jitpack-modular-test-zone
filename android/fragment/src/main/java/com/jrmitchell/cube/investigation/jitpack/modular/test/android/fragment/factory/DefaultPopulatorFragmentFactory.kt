package com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment.factory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity.DefaultFragmentActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment.DefaultFragmentDisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.LoadingIndicator

/**
 * Implementation of [FragmentFactory] for creating [DefaultFragmentDisplayTarget] instances
 *
 * @param actionResolver action resolver for the [DefaultFragmentDisplayTarget]s to use
 * @param imageLoader image loader for the [DefaultFragmentDisplayTarget] UI
 * @param populator populator to populate the [DefaultFragmentDisplayTarget]s
 * @param loadingIndicator loading indicator for population
 * @param condition condition for determining when to use default [FragmentFactory] instantiation and when to create [DefaultFragmentDisplayTarget] instances
 *  [instantiate] will call super.instantiate when [condition].shouldUseDefaultInstantiation returns true, and [fragmentWithoutId] when it returns false
 */
class DefaultPopulatorFragmentFactory(
	private var actionResolver: AndroidActionResolver,
	private var imageLoader: AndroidImageLoader,
	private var populator: DisplayPopulator,
	private var loadingIndicator: LoadingIndicator,
	private var condition: Condition
) : FragmentFactory() {
	
	/**
	 * Interface for determining whether default [FragmentFactory] instantiation should be used
	 */
	interface Condition {
		/**
		 * Determine whether default [FragmentFactory] instantiation should be used
		 */
		fun shouldUseDefaultInstantiation(classLoader: ClassLoader, className: String): Boolean
		
		companion object {
			/**
			 * Implementation of [Condition] that never uses default [FragmentFactory] instantiation
			 */
			object NeverDefault : Condition {
				override fun shouldUseDefaultInstantiation(classLoader: ClassLoader, className: String) = false
			}
		}
	}
	
	override fun instantiate(classLoader: ClassLoader, className: String): Fragment = if (condition.shouldUseDefaultInstantiation(classLoader, className)) {
		super.instantiate(classLoader, className)
	}
	else {
		fragmentWithoutId()
	}
	
	/**
	 * Creates an instance of [DefaultFragmentDisplayTarget] based on this factory's [actionResolver] and [imageLoader], which will populate from its [populator] based on the [actionId]
	 */
	fun fragmentForId(actionId: String?) = fragmentWithoutId().apply {
		arguments = Bundle().apply {
			putString(DefaultFragmentActivity.ACTION_ID_EXTRA_KEY, actionId)
		}
	}
	
	/**
	 * Creates an instance of [DefaultFragmentDisplayTarget] based on this factory's [actionResolver] and [imageLoader], which will populate from its [populator]
	 */
	private fun fragmentWithoutId() = DefaultFragmentDisplayTarget(actionResolver, imageLoader) {
		val actionId = arguments?.getString(DefaultFragmentActivity.ACTION_ID_EXTRA_KEY)
		populator.populateDisplayFromUri(actionId, this, loadingIndicator)
	}
}