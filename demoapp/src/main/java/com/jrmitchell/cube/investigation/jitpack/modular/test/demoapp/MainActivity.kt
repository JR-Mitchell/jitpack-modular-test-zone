package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	
	private val uiType get() = binding.uiSpinner.selectedItem as UiType
	private val populatorType get() = binding.populatorSpinner.selectedItem as PopulatorType
	private val imageLoaderType get() = binding.imageLoaderSpinner.selectedItem as ImageLoaderType
	
	private fun getIntent(cls : Class<out Activity>) = Intent(this, cls).apply {
		putExtra(PopulatorType::class.simpleName, populatorType.name)
		putExtra(ImageLoaderType::class.simpleName, imageLoaderType.name)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		binding.uiSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, UiType.values())
		binding.populatorSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, PopulatorType.values())
		binding.imageLoaderSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ImageLoaderType.values())
		
		binding.goButton.setOnClickListener {
			when(uiType) {
				UiType.ACTIVITY ->
					startActivity(getIntent(ActivityActivity::class.java).apply {
						addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
					})
				UiType.FRAGMENT -> startActivity(getIntent(FragmentActivity::class.java))
			}
		}
	}
}