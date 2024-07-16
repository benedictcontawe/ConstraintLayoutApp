package com.app.constraintlayoutapp

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.app.constraintlayoutapp.databinding.MainBinder
import com.google.android.material.imageview.ShapeableImageView

public class MainActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private val TAG : String = MainActivity::class.java.getSimpleName()
    }

    private var binder : MainBinder? = null
    private val viewModel : MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binder?.floatingActionButtonWheel?.setOnClickListener(this@MainActivity)
        binder?.floatingActionButtonFlapper?.setOnClickListener(this@MainActivity)
        viewModel.observeFlapper(1, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewNorth?.setImageDrawable(drawable) }
        viewModel.observeFlapper(2, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewNorthEast?.setImageDrawable(drawable) }
        viewModel.observeFlapper(3, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewEast?.setImageDrawable(drawable) }
        viewModel.observeFlapper(4, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewSouthEast?.setImageDrawable(drawable) }
        viewModel.observeFlapper(5, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewSouth?.setImageDrawable(drawable) }
        viewModel.observeFlapper(6, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewSouthWest?.setImageDrawable(drawable) }
        viewModel.observeFlapper(7, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewWest?.setImageDrawable(drawable) }
        viewModel.observeFlapper(8, getTheme()).observe(this@MainActivity) { drawable : Drawable? -> binder?.imageViewNorthWest?.setImageDrawable(drawable) }
        viewModel.observeCenter().observe(this@MainActivity) { angle : Float -> setAnimateRotate(binder?.imageViewCenter, angle) }
        viewModel.observeNorthSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewNorth, angle) }
        viewModel.observeNorthEastSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewNorthEast, angle) }
        viewModel.observeEastSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewEast, angle) }
        viewModel.observeSouthEastSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewSouthEast, angle) }
        viewModel.observeSouthSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewSouth, angle) }
        viewModel.observeSouthWestSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewSouthWest, angle) }
        viewModel.observeWestSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewWest, angle) }
        viewModel.observeNorthWestSector().observe(this@MainActivity) { angle : Float -> setAnimateAngle(binder?.imageViewNorthWest, angle) }
        viewModel.observeWheel().observe(this@MainActivity, object : Observer<Boolean?> {
            override fun onChanged(isRotate : Boolean?) {
                binder?.floatingActionButtonWheel?.setImageDrawable(viewModel.getAnimatedVectorDrawableCompat(isRotate!!))
                val animatable = binder!!.floatingActionButtonWheel.getDrawable() as Animatable2Compat
                animatable.start()
            }
        })
        viewModel.observeFlapper().observe(this@MainActivity, object : Observer<Boolean?> {
            override fun onChanged(isFlap : Boolean?) {
                binder?.floatingActionButtonFlapper?.setImageDrawable(viewModel.getAnimatedVectorDrawableCompat(isFlap!!))
                val animatable = binder!!.floatingActionButtonFlapper.getDrawable() as Animatable2Compat
                animatable.start()
            }
        })
    }

    private fun setAnimateRotate(imageView : ShapeableImageView?, angle: Float) {
        Log.d(TAG,"setAnimateRotate " + imageView!!.getRotation() + " " + angle)
        val objectAnimator : ObjectAnimator =
            if (imageView!!.getRotation() < angle)
                ObjectAnimator.ofFloat(imageView, View.ROTATION, imageView!!.getRotation(), angle)
            else
                ObjectAnimator.ofFloat(imageView, View.ROTATION, imageView!!.getRotation(), 360 + angle)
        objectAnimator.setDuration(500L)
        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation : Animator) {  }
            override fun onAnimationEnd(animation : Animator) {
                imageView?.setRotation(angle)
            }
            override fun onAnimationCancel(animation : Animator) {  }
            override fun onAnimationRepeat(animation : Animator) {  }
        })
        objectAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                Log.d(TAG, "onAnimationUpdate ${animation.getAnimatedValue()}");
            }
        })
        objectAnimator.start()
    }

    private fun setImageAngle(image : ShapeableImageView?, angle : Float) {
        val layoutParams : ConstraintLayout.LayoutParams = image?.getLayoutParams() as ConstraintLayout.LayoutParams
        layoutParams.circleAngle = angle
        image.setLayoutParams(layoutParams)
    }

    private fun setAnimateAngle(imageView: ShapeableImageView?, angle: Float) {
        val oldLayoutParams = imageView?.layoutParams as LayoutParams
        Log.d(TAG,"setAnimateAngle " + oldLayoutParams.circleAngle + " " + angle)
        val valueAnimator : ValueAnimator =
            if (oldLayoutParams.circleAngle < angle)
                ValueAnimator.ofFloat(oldLayoutParams.circleAngle, angle)
            else
                ValueAnimator.ofFloat(oldLayoutParams.circleAngle, 360 + angle)
        valueAnimator.setDuration(500L)
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation : ValueAnimator) {
                val newAngle : Float = animation.getAnimatedValue() as Float
                val newLayoutParams : LayoutParams = imageView.layoutParams as LayoutParams
                if (newAngle < 360) newLayoutParams.circleAngle = newAngle
                else newLayoutParams.circleAngle = 360 - newAngle
                imageView.setLayoutParams(newLayoutParams)
                Log.d(TAG, "onAnimationUpdate " + newLayoutParams.circleAngle)
            }
        })
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.repeatCount = 0
        valueAnimator.start()
    }

    override fun onClick(view : View?) {
        if (view?.getId() == binder?.floatingActionButtonWheel?.getId()) {
            viewModel.toggleWheel();
        } else if (view?.getId() == binder?.floatingActionButtonFlapper?.getId()) {
            viewModel.toggleFlapper();
        }
    }
}