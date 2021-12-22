package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity.DefaultActivityDisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.adapter.ButtonAdapter
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.images.picasso.PicassoImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityActivityBinding

class ActivityActivity : DefaultActivityDisplayTarget<PicassoImageLoader>() {
	lateinit var binding: ActivityActivityBinding
	override val buttonRecyclerView: RecyclerView get() = binding.display.findViewById(R.id.display_button_list)
	override lateinit var buttonAdapter: ButtonAdapter
	override val imageLoader = PicassoImageLoader
	override val imageView: ImageView get() = binding.display.findViewById(R.id.display_image)
	override val loadingUi: View get() = binding.loading
	override val titleView: TextView get() = binding.display.findViewById(R.id.display_title)
	
	lateinit var populator: DisplayPopulator
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		populator = PopulatorType.valueOfOrDefault(intent.getStringExtra(PopulatorType::class.simpleName)).activityGetter(this)
		
		initialiseAdapter()
		
		populator.populateDisplayFromUri(getActionId() ?: "screen0", this, this)
	}
	
	override fun screenIntentGenerator(actionId: String) = super.screenIntentGenerator(actionId).apply {
		putExtra(PopulatorType::class.simpleName, intent.getStringExtra(PopulatorType::class.simpleName))
	}
	
}