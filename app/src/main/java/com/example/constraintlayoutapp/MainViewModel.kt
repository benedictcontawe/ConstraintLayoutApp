package com.example.constraintlayoutapp

import android.app.Application
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
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
    private val liveNorthSector : MutableLiveData<Float> = MutableLiveData(0f)
    private val liveNorthEastSector : MutableLiveData<Float> = MutableLiveData(45f)
    private val liveEastSector : MutableLiveData<Float> = MutableLiveData(90f)
    private val liveSouthEastSector : MutableLiveData<Float> = MutableLiveData(135f)
    private val liveSouthSector : MutableLiveData<Float> = MutableLiveData(180f)
    private val liveSouthWestSector : MutableLiveData<Float> = MutableLiveData(225f)
    private val liveWestSector : MutableLiveData<Float> = MutableLiveData(270f)
    private val liveNorthWestSector : MutableLiveData<Float> = MutableLiveData(315f)

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
        liveCenter.postValue(rotateAngle(liveCenter.getValue()?.plus(ANGLE_INTERVAL)))
        liveNorthSector.postValue(rotateAngle(liveNorthSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveNorthEastSector.postValue(rotateAngle(liveNorthEastSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveEastSector.postValue(rotateAngle(liveEastSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveSouthEastSector.postValue(rotateAngle(liveSouthEastSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveSouthSector.postValue(rotateAngle(liveSouthSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveSouthWestSector.postValue(rotateAngle(liveSouthWestSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveWestSector.postValue(rotateAngle(liveWestSector.getValue()?.plus(ANGLE_INTERVAL)))
        liveNorthWestSector.postValue(rotateAngle(liveNorthWestSector.getValue()?.plus(ANGLE_INTERVAL)))
    }

    private fun rotateAngle(angle : Float?) : Float {
        return if (angle == null) 18f
        else if ((angle + 18f) > 360 ) (angle + 18f) - 360
        else angle + 18f
    }

    public fun observeFlapper(sector: Int, theme : Resources.Theme) : LiveData<Drawable?> {
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

    public fun observeNorthSector() : LiveData<Float> = liveNorthSector

    public fun observeNorthEastSector() : LiveData<Float> = liveNorthEastSector

    public fun observeEastSector() : LiveData<Float> = liveEastSector

    public fun observeSouthEastSector() : LiveData<Float> = liveSouthEastSector

    public fun observeSouthSector() : LiveData<Float> = liveSouthSector

    public fun observeSouthWestSector() : LiveData<Float> = liveSouthWestSector

    public fun observeWestSector() : LiveData<Float> = liveWestSector

    public fun observeNorthWestSector() : LiveData<Float> = liveNorthWestSector
    //region Flapper and Wheel
    public fun toggleWheel() {
        if (java.lang.Boolean.TRUE == liveRotateWheel.getValue()) {
            Log.d(TAG, "toggleWheel true")
            liveRotateWheel.setValue(false)
            startWheel()
        } else {
            Log.d(TAG, "toggleWheel false")
            liveRotateWheel.setValue(true)
        }
    }

    public fun toggleFlapper() {
        if (java.lang.Boolean.TRUE == liveRotateFlapper.getValue()) {
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

    public fun observeWheel() : LiveData<Boolean> {
        return liveRotateWheel
    }

    public fun observeFlapper() : LiveData<Boolean> {
        return liveRotateFlapper
    }
    //endregion
    override fun onCleared() {
        super.onCleared()
    }
}