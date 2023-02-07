package com.example.constraintlayoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.constraintlayoutapp.databinding.MainBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainBinder binder;
    private MainViewModel viewModel;
    private ConstraintSet constraintSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binder.setViewModel(viewModel);
        binder.setLifecycleOwner(this);
        constraintSet = new ConstraintSet();

        binder.floatingActionButtons.floatingActionButtonAndroid.setOnClickListener(this::onClick);
        binder.floatingActionButtons.floatingActionButtonAndroid.setOnTouchListener(this::onTouch);

        binder.getViewModel().observeFABVisibility().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isShowed) {
                if (isShowed) showExtendedFAB();
                else hideExtendedFab();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binder.floatingActionButtons.floatingActionButtonAndroid)
            binder.getViewModel().setLiveFABVisibility();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binder.floatingActionButtons.constraintLayout.getLayoutParams();
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                binder.getViewModel().setDownRawX(motionEvent.getRawX());
                binder.getViewModel().setDownRawY(motionEvent.getRawY());
                binder.getViewModel().setDx(binder.floatingActionButtons.constraintLayout.getX() - binder.getViewModel().getDownRawX());
                binder.getViewModel().setDy(binder.floatingActionButtons.constraintLayout.getY() - binder.getViewModel().getDownRawY());
                return false; //Ripple Effect
            case MotionEvent.ACTION_MOVE:
                binder.getViewModel().setViewDimension(binder.floatingActionButtons.constraintLayout);
                binder.getViewModel().setParentDimension(binder.floatingActionButtons.constraintLayout.getParent());
                binder.getViewModel().setNewX(motionEvent.getRawX(), layoutParams);
                binder.getViewModel().setNewY(motionEvent.getRawY(), layoutParams);
                binder.floatingActionButtons.constraintLayout.animate()
                        .x( binder.getViewModel().getNewX() )
                        .y( binder.getViewModel().getNewY() )
                        .setDuration(0)
                        .start();
                return true; //Consumed
            case MotionEvent.ACTION_UP:
                float upRawX = motionEvent.getRawX();
                float upRawY = motionEvent.getRawY();

                float upDX = upRawX - binder.getViewModel().getDownRawX();
                float upDY = upRawY - binder.getViewModel().getDownRawY();

                return binder.getViewModel().canClick(binder.floatingActionButtons.constraintLayout, upDX, upDY);
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    private void showExtendedFAB() {
        binder.floatingActionButtons.extendedFloatingActionButtonOne.show();
        binder.floatingActionButtons.extendedFloatingActionButtonTwo.show();
        binder.floatingActionButtons.extendedFloatingActionButtonThree.show();
        binder.floatingActionButtons.extendedFloatingActionButtonFour.show();
    }

    private void hideExtendedFab() {
        binder.floatingActionButtons.extendedFloatingActionButtonOne.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonTwo.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonThree.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonFour.hide();
    }
}