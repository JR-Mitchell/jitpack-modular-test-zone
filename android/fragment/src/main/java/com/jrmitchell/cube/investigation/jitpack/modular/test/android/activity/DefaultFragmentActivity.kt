package com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidLoadingIndicator
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MatchActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MultiActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment.factory.DefaultPopulatorFragmentFactory
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator

/**
 * Single-[AppCompatActivity] implementation for containing fragment displays
 */
abstract class DefaultFragmentActivity<T : View, L : AndroidImageLoader> : AppCompatActivity(), AndroidLoadingIndicator {
	
	companion object {
		const val ACTION_ID_EXTRA_KEY = "ACTION_ID_EXTRA_KEY"
	}
	
	abstract val fragmentContainerView: T
	abstract val imageLoader: L
	abstract val populator: DisplayPopulator
	abstract val fragmentFactory: DefaultPopulatorFragmentFactory
	
	/**
	 * Get an instance of [DefaultPopulatorFragmentFactory] for this activity with the given condition
	 *
	 * @param condition the condition under which the [DefaultPopulatorFragmentFactory] should use default fragment loading
	 * @return an instance of [DefaultPopulatorFragmentFactory] for this activity with the given [condition]
	 */
	protected fun getDefaultFragmentFactory(condition: DefaultPopulatorFragmentFactory.Condition) = DefaultPopulatorFragmentFactory(actionResolver, imageLoader, populator, this, condition)
	/**
	 * Get an instance of [DefaultPopulatorFragmentFactory] for this activity which only creates instances of DefaultFragmentDisplayTarget
	 *
	 * @return an instance of [DefaultPopulatorFragmentFactory] for this activity
	 */
	protected fun getDefaultFragmentFactory() = getDefaultFragmentFactory(DefaultPopulatorFragmentFactory.Condition.Companion.NeverDefault)
	/**
	 * Get an instance of [DefaultPopulatorFragmentFactory] for this activity with the given condition
	 *
	 * @param condition the condition under which the [DefaultPopulatorFragmentFactory] should use default fragment loading
	 * @return an instance of [DefaultPopulatorFragmentFactory] for this activity with the given [condition]
	 */
	protected inline fun getDefaultFragmentFactory(crossinline condition: (classLoader: ClassLoader, className: String) -> Boolean) =
		getDefaultFragmentFactory(object : DefaultPopulatorFragmentFactory.Condition {
			override fun shouldUseDefaultInstantiation(classLoader: ClassLoader, className: String) = condition(classLoader, className)
		})
	
	val actionResolver = MultiActionResolver().apply {
		resolvers.add(MatchActionResolver("""screen.+""".toRegex()) { _, actionId ->
			supportFragmentManager.beginTransaction()
				.replace(fragmentContainerView.id, fragmentFactory.fragmentForId(actionId))
				.addToBackStack(null)
				.commit()
		})
		resolvers.add(MatchActionResolver("""back""".toRegex()) { _, _ -> supportFragmentManager.popBackStack() })
		resolvers.add(MatchActionResolver("""close""".toRegex()) { _, _ -> finish() })
	}
}