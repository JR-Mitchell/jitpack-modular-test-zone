package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver

import android.content.Context
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData

/**
 * Implementation of [AndroidActionResolver] which returns true and runs an action iff the action ID matches a regex pattern
 *
 * @param regex the regex pattern that action IDs to be actioned should meet
 * @param action the action to call for any action whose ID matches [regex]
 */
class MatchActionResolver(private val regex: Regex, private val action : (context : Context, actionId : String) -> Unit) : AndroidActionResolver {
	override fun resolveAction(context: Context, action: ActionData?): Boolean = if (action?.actionId?.matches(regex) == true)
	{
		action(context, action.actionId)
		true
	}
	else false
}