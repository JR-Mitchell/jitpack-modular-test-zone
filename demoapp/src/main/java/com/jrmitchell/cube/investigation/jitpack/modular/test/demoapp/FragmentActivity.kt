package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.activity.DefaultFragmentActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.fragment.factory.DefaultPopulatorFragmentFactory
import com.jrmitchell.cube.investigation.jitpack.modular.test.android.images.picasso.PicassoImageLoader
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.abstract.DisplayPopulator
import com.jrmitchell.cube.investigation.jitpack.modular.test.core.data.ActionData
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityFragmentBinding

class FragmentActivity : DefaultFragmentActivity<FragmentContainerView, PicassoImageLoader>() {
	lateinit var binding: ActivityFragmentBinding
	override val imageLoader = PicassoImageLoader
	override val fragmentContainerView: FragmentContainerView get() = binding.containerView
	override val loadingUi: View get() = binding.loading
	override lateinit var fragmentFactory: DefaultPopulatorFragmentFactory
	override lateinit var populator : DisplayPopulator
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityFragmentBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		populator = PopulatorType.valueOfOrDefault(intent.getStringExtra(PopulatorType::class.simpleName)).fragmentGetter(this)
		
		fragmentFactory = getDefaultFragmentFactory()
		supportFragmentManager.fragmentFactory = fragmentFactory
		
		actionResolver.resolveAction(this, ActionData("fragmentO"))
	}
	
}