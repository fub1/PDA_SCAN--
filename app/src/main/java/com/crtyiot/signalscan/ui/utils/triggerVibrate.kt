package com.crtyiot.signalscan.ui.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

// 函数来触发震动
fun triggerVibrate(    context: Context) {

    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // TODO：需要更新到协程中调用，否则会影响界面交互
        Thread.sleep(1199)
        val effect = VibrationEffect.createOneShot(120, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(effect)
        Thread.sleep(180)
        vibrator.vibrate(effect)
        Thread.sleep(180)
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(70)
    }
}