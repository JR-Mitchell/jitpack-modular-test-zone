package com.jrmitchell.cube.investigation.jitpack.modular.test.core.data

/**
 * Data class representing data that can be displayed in a demo page
 *
 * @param imageData the image to display
 * @param imageContentDescription the content description for the image
 * @param titleText the title to display
 * @param topButton the data to display for the top button
 */
data class DisplayData(val imageData: ImageData, val imageContentDescription: String, val titleText: String, val topButton : ButtonData)