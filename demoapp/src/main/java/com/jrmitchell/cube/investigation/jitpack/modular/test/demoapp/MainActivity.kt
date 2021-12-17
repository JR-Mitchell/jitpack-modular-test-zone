package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity.DefaultActivityDisplayTarget
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityMainBinding

class MainActivity : DefaultActivityDisplayTarget<PlaceholderImageLoader>() {
	lateinit var binding : ActivityMainBinding
	override val buttonView: TextView get() = binding.display.findViewById(R.id.display_button)
	override val imageLoader = PlaceholderImageLoader()
	override val imageView: ImageView get() = binding.display.findViewById(R.id.display_image)
	override val loadingUi: View get() = binding.loading
	override val titleView: TextView get() = binding.display.findViewById(R.id.display_title)
	
	val populator = PlaceholderDisplayPopulator()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		populator.populateDisplayFromUri(getActionId(), this)
	}
	
}