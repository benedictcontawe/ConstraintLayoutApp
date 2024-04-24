package com.example.constraintlayoutapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.example.constraintlayoutapp.databinding.MainBinder
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity(), OnClickListener {

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
        viewModel.observeCenter().observe(this@MainActivity) { angle : Float -> binder?.imageViewCenter?.setRotation(angle) }
        viewModel.observeNorthSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewNorth, angle) }
        viewModel.observeNorthEastSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewNorthEast, angle) }
        viewModel.observeEastSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewEast, angle) }
        viewModel.observeSouthEastSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewSouthEast, angle) }
        viewModel.observeSouthSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewSouth, angle) }
        viewModel.observeSouthWestSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewSouthWest, angle) }
        viewModel.observeWestSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewWest, angle) }
        viewModel.observeNorthWestSector().observe(this@MainActivity) { angle : Float -> setImageCircleAngle(binder?.imageViewNorthWest, angle) }
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

    private fun setImageCircleAngle(image : ShapeableImageView?, angle : Float) {
        val layoutParams : ConstraintLayout.LayoutParams = image?.getLayoutParams() as ConstraintLayout.LayoutParams
        layoutParams.circleAngle = angle
        image.setLayoutParams(layoutParams)
    }

    override fun onClick(view : View?) {
        if (view?.getId() == binder?.floatingActionButtonWheel?.getId()) {
            viewModel.toggleWheel();
        } else if (view?.getId() == binder?.floatingActionButtonFlapper?.getId()) {
            viewModel.toggleFlapper();
        }
    }
}