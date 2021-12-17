package com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.R
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.resolver.MultiActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.view.holder.ButtonViewHolder
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ButtonData

/**
 * [RecyclerView] adapter for a list of Buttons with data specified by [ButtonData]
 */
class ButtonAdapter : RecyclerView.Adapter<ButtonViewHolder>() {
	/**
	 * List of [ButtonData] for each of the buttons
	 */
	var data : List<ButtonData> = emptyList()
		@SuppressLint("NotifyDataSetChanged")
		set(value) {
			field = value
			notifyDataSetChanged()
		}
	
	var actionResolver : AndroidActionResolver = MultiActionResolver()
		@SuppressLint("NotifyDataSetChanged")
		set(value) {
			field = value
			notifyDataSetChanged()
		}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
		val button = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
		return ButtonViewHolder(button as TextView)
	}
	
	override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
		holder.populate(data[position], actionResolver)
	}
	
	override fun getItemCount(): Int = data.size
}