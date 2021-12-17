package com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidDisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MatchActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MultiActionResolver

/**
 * [AppCompatActivity] and [AndroidDisplayTarget] implementation based on some assumptions of how actions will be resolved and errors shown
 */
abstract class DefaultActivityDisplayTarget <L : AndroidImageLoader> : AppCompatActivity(), AndroidDisplayTarget<MultiActionResolver, L> {
	
	companion object {
		const val ACTION_ID_EXTRA_KEY = "ACTION_ID_EXTRA_KEY"
	}
	
	override val actionResolver = MultiActionResolver().apply {
		resolvers.add(activityIntentActionResolver("""activity""".toRegex()) { actionId ->
			Intent(this@DefaultActivityDisplayTarget, this@DefaultActivityDisplayTarget::class.java).also {
				it.putExtra(ACTION_ID_EXTRA_KEY, actionId)
			}
		})
		resolvers.add(MatchActionResolver("""back""".toRegex()) { _, _ -> finish() })
		resolvers.add(MatchActionResolver("""close""".toRegex()) { _, _ -> finishAffinity() })
	}
	
	override fun displayError(throwable: Throwable?) {
		super.displayError(throwable)
		Toast.makeText(this, throwable?.localizedMessage ?: "Unknown error", Toast.LENGTH_LONG).show()
	}
	
	protected fun getActionId() = intent.getStringExtra(ACTION_ID_EXTRA_KEY)
}