package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver

import android.content.Context
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData

/**
 * Implementation of [AndroidActionResolver] which resolves to the first action in a list that returns true
 */
class MultiActionResolver : AndroidActionResolver {
	val resolvers : MutableList<AndroidActionResolver> = mutableListOf()
	override fun resolveAction(context: Context, action: ActionData?) = resolvers.any { it.resolveAction(context, action) }
}