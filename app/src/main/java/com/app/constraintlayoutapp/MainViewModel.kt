package com.app.constraintlayoutapp

import android.app.Application
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.coroutines.delay

class MainViewModel : AndroidViewModel {

    companion object {
        private val TAG = MainViewModel::class.java.getSimpleName()
        private const val COUNTDOWN_WHEEL_INTERVAL : Long = 500
        private const val COUNTDOWN_FLAPPER_INTERVAL : Long = 500
        private const val ANGLE_INTERVAL : Float = 6f
    }

    private val liveRotateFlapper : MutableLiveData<Boolean> = MutableLiveData(true)
    private val liveRotateWheel : MutableLiveData<Boolean> = MutableLiveData(true)
    private val liveFlapper : MutableLiveData<Int> = MutableLiveData(1)
    private val liveCenter : MutableLiveData<Float> = MutableLiveData(0f)

    constructor(application : Application) : super(application) {

    }

    private fun startWheel() {
        Log.d(TAG, "startWheel")
        CoroutinesUtils.default(this@MainViewModel, work = {
            while (liveRotateWheel.getValue() == false) {
                delay(COUNTDOWN_WHEEL_INTERVAL)
                updateWheel()
            }
        })
    }

    private fun startFlapper() {
        Log.d(TAG, "startFlapper")
        CoroutinesUtils.default(this@MainViewModel, work = {
            while (liveRotateFlapper.getValue() == false) {
                delay(COUNTDOWN_FLAPPER_INTERVAL)
                updateFlapper()
            }
        })
    }

    private fun updateFlapper() {
        if (liveFlapper.getValue() == 1) liveFlapper.postValue(8)
        else if (liveFlapper.getValue() == 2) liveFlapper.postValue(1)
        else if (liveFlapper.getValue() == 3) liveFlapper.postValue(2)
        else if (liveFlapper.getValue() == 4) liveFlapper.postValue(3)
        else if (liveFlapper.getValue() == 5) liveFlapper.postValue(4)
        else if (liveFlapper.getValue() == 6) liveFlapper.postValue(5)
        else if (liveFlapper.getValue() == 7) liveFlapper.postValue(6)
        else if (liveFlapper.getValue() == 8) liveFlapper.postValue(7)
    }

    private fun updateWheel() {
        liveCenter.postValue(rotateAngle(liveCenter.getValue(), ANGLE_INTERVAL))
    }

    private fun rotateAngle(angle : Float?, addedAngle : Float) : Float {
        return if (angle == null) addedAngle
        else if ((angle + addedAngle) > 360 ) (angle + addedAngle) - 360
        else angle + addedAngle
    }

    public fun observeFlapper(sector : Int, theme : Resources.Theme) : LiveData<Drawable?> {
        return liveFlapper.map { input : Int? ->
            if (input != null && input == sector) {
                return@map ResourcesCompat.getDrawable(getApplication<Application>().getResources(), R.drawable.ic_circle_with_aura_blue, theme)
            } else if (input != null && input != sector) {
                return@map ResourcesCompat.getDrawable(getApplication<Application>().getResources(), R.drawable.ic_circle_blue, theme)
            } else {
                return@map null
            }
        }
    }

    public fun observeCenter() : LiveData<Float> = liveCenter
    //region Flapper and Wheel
    public fun toggleWheel() {
        if (liveRotateWheel.getValue() == true) {
            Log.d(TAG, "toggleWheel true")
            liveRotateWheel.setValue(false)
            startWheel()
        } else {
            Log.d(TAG, "toggleWheel false")
            liveRotateWheel.setValue(true)
        }
    }

    public fun toggleFlapper() {
        if (liveRotateFlapper.getValue() == true) {
            Log.d(TAG, "toggleFlapper true")
            liveRotateFlapper.setValue(false)
            startFlapper()
        } else {
            Log.d(TAG, "toggleFlapper false")
            liveRotateFlapper.setValue(true)
        }
    }

    public fun getAnimatedVectorDrawableCompat(isPlay : Boolean) : AnimatedVectorDrawableCompat? {
        val play = AnimatedVectorDrawableCompat.create(getApplication(), R.drawable.anim_play_to_pause)
        val pause = AnimatedVectorDrawableCompat.create(getApplication(), R.drawable.anim_pause_to_play)
        return if (isPlay == true) pause else play
    }

    public fun observeWheel() : LiveData<Boolean> = liveRotateWheel

    public fun observeFlapper() : LiveData<Boolean> = liveRotateFlapper
    //endregion
    override fun onCleared() {
        super.onCleared()
    }
}