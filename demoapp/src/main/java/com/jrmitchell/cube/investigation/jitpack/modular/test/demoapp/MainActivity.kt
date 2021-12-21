package com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.jrmitchell.cube.investigation.jitpack.modular.test.demoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		binding.uiSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, UiType.values())
		
		binding.goButton.setOnClickListener {
			when(binding.uiSpinner.selectedItem as UiType) {
				UiType.ACTIVITY ->
					startActivity(Intent(this, ActivityActivity::class.java).apply {
						addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
					})
				UiType.FRAGMENT -> startActivity(Intent(this, FragmentActivity::class.java))
			}
		}
	}
}