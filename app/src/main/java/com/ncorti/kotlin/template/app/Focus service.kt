package com.ncorti.kotlin.template.app

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class FocusService : AccessibilityService() {

    // The UPDATED keywords that trigger the block
    private val blockList = listOf("porn", "sex", "nsfw", "adult", "milf", "xxx", "xhamster", "xham", "hentai")

    // Your safe study topics that override the block
    private val allowList = listOf("matrices", "thermodynamics", "quantum numbers", "plus one physics", "class 11","class +2")

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val screenText = event.text.joinToString(" ").lowercase()

        for (badWord in blockList) {
            if (screenText.contains(badWord)) {
                
                var isStudyTopic = false
                for (goodWord in allowList) {
                    if (screenText.contains(goodWord)) {
                        isStudyTopic = true
                        break
                    }
                }

                if (!isStudyTopic) {
                    // Send the user instantly back to the home screen
                    performGlobalAction(GLOBAL_ACTION_HOME)
                    Toast.makeText(applicationContext, "Focus on Goals: Content Blocked!", Toast.LENGTH_SHORT).show()
                    return 
                }
            }
        }
    }

    override fun onInterrupt() {}
                  }
  
