package com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidActionResolver
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidDisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.adapter.ButtonAdapter

/**
 * [Fragment] [AndroidDisplayTarget] implementation
 */
class DefaultFragmentDisplayTarget<T : AndroidActionResolver, L : AndroidImageLoader>(
	override var actionResolver: T,
	override var imageLoader: L,
	val callback: DefaultFragmentDisplayTarget<T, L>.() -> Unit
) : Fragment(R.layout.display_view), AndroidDisplayTarget<T, L> {
	
	override val buttonRecyclerView: RecyclerView? get() = view?.findViewById(R.id.display_button_list)
	override lateinit var buttonAdapter: ButtonAdapter
	
	override fun displayError(throwable: Throwable?) {
		Toast.makeText(context, throwable?.localizedMessage ?: "Unknown error", Toast.LENGTH_LONG).show()
	}
	
	override val imageView: ImageView? get() = view?.findViewById(R.id.display_image)
	override val titleView: TextView? get() = view?.findViewById(R.id.display_title)
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initialiseAdapter()
		callback()
	}
}