package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity.DefaultFragmentActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.core.abstract.AndroidImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment.factory.DefaultPopulatorFragmentFactory
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityFragmentBinding

class FragmentActivity : DefaultFragmentActivity<FragmentContainerView, AndroidImageLoader>() {
	lateinit var binding: ActivityFragmentBinding
	override val fragmentContainerView: FragmentContainerView get() = binding.containerView
	override val loadingUi: View get() = binding.loading
	override lateinit var fragmentFactory: DefaultPopulatorFragmentFactory
	
	override lateinit var populator: DisplayPopulator
	override lateinit var imageLoader: AndroidImageLoader
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityFragmentBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		populator = PopulatorType.valueOfOrDefault(intent.getStringExtra(PopulatorType::class.simpleName)).fragmentGetter(this)
		imageLoader = ImageLoaderType.valueOfOrDefault(intent.getStringExtra(ImageLoaderType::class.simpleName)).fragmentGetter(this)
		
		fragmentFactory = getDefaultFragmentFactory()
		supportFragmentManager.fragmentFactory = fragmentFactory
		
		actionResolver.resolveAction(this, ActionData("screen0"))
	}
	
}