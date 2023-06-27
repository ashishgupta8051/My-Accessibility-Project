package com.example.myapplication.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.accessibility.AccessibilityEvent
import com.example.myapplication.utils.showLog

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        showLog("onAccessibilityEvent")

        val packageName  = event?.packageName.toString()
        val packageManager = this.packageManager
        try {
            val applicationInfo = packageManager.getApplicationInfo(packageName,0)
            val applicationLabel = packageManager.getApplicationLabel(applicationInfo)
            showLog("App Name: $applicationLabel")
        }catch (e: PackageManager.NameNotFoundException){
            e.printStackTrace()
        }

        packageManager.getApplicationLabel(applicationInfo)

    }

    override fun onInterrupt() {
        showLog("onInterrupt : Something went wrong")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        val info = AccessibilityServiceInfo()
        info.apply {
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN
            notificationTimeout = 100
        }

        this.serviceInfo = info

        showLog("onServiceConnected")
    }
}