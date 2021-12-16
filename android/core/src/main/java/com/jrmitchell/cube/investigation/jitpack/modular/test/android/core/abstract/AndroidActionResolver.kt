package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract

import android.content.Context
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData

/**
 * Interface for resolving actions for Android views
 */
interface AndroidActionResolver {
	/**
	 * Resolve an action triggered by an Android view
	 *
	 * @param context the context within which the action was triggered
	 * @param action the action data associated with the triggering view
	 */
	fun resolveAction(context: Context, action: ActionData?) : Boolean
}