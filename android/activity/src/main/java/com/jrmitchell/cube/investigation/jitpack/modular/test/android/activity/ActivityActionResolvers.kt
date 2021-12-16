package com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity

import android.content.Intent
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MatchActionResolver

/**
 * Get a [MatchActionResolver] that generates and starts an activity for any matched id
 *
 * @param regex the regex to match with
 * @param intentGenerator method for generating an intent for matched action ids
 */
fun activityIntentActionResolver(regex: Regex, intentGenerator : (actionId : String) -> Intent) = MatchActionResolver(regex) { context, actionId ->
	context.startActivity(intentGenerator(actionId))
}