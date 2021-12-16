package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver

import android.content.Context
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData

class MultiActionResolver : AndroidActionResolver {
	val resolvers : MutableList<AndroidActionResolver> = mutableListOf()
	override fun resolveAction(context: Context, action: ActionData?) = resolvers.any { it.resolveAction(context, action) }
}