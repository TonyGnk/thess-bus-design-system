package com.example.benchmark

import android.os.SystemClock
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val rule  = BaselineProfileRule()

    @Test
    fun generate() = rule.collect(
        packageName = "com.tonyGnk.thessBus.designSystem.mobile"
    ){
        pressHome()
        startActivityAndWait()

        // Helper function to find and click elements safely
        fun findAndClick(text: String, timeout: Long = 5000) {
            val element = device.wait(Until.findObject(By.text(text)), timeout)
            element?.click() ?: throw IllegalStateException("Could not find element with text: $text")
            SystemClock.sleep(1500)
        }

        findAndClick("Components")
        findAndClick("Navigation Bar")
        device.pressBack()
        SystemClock.sleep(1500)
        device.pressBack()
        SystemClock.sleep(1500)

        findAndClick("Features")

        SystemClock.sleep(1500)
        findAndClick("Directions")

        // Scroll at the center of the screen
        val scrollable = UiScrollable(UiSelector().scrollable(true))
        scrollable.setAsHorizontalList()

        // Scroll to the end as per original logic
        scrollable.flingToEnd(50)
        SystemClock.sleep(3000)

        // Scroll back to the top to ensure "Preview" becomes visible
        scrollable.scrollToBeginning(50) // Change from fling to scroll to ensure we're at the top
        SystemClock.sleep(3000)          // Wait for the scroll to finish

    }
}