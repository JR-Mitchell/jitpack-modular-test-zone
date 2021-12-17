package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.view.holder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData

/**
 * ViewHolder for a single button with data specified by [ButtonData]
 */
class ButtonViewHolder(private val button : TextView) : RecyclerView.ViewHolder(button)
{
	fun populate(data: ButtonData, actionResolver: AndroidActionResolver)
	{
		button.text = data.buttonText
		button.setOnClickListener {
			actionResolver.resolveAction(it.context, data.buttonAction)
		}
	}
}