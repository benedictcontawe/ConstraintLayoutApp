package com.example.constraintlayoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.constraintlayoutapp.databinding.MainBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainBinder binder;
    private MainViewModel viewModel;
    private Animation rotationOpen, rotationClose, fromBottom, toBottom;
    private ConstraintSet constraintSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binder.setViewModel(viewModel);
        binder.setLifecycleOwner(this);

        binder.floatingActionButtons.floatingActionButtonAndroid.setOnClickListener(this::onClick);
        binder.floatingActionButtons.floatingActionButtonAndroid.setOnTouchListener(this::onTouch);

        rotationOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation);
        rotationClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation);

        constraintSet = new ConstraintSet();

        binder.getViewModel().observeFABVisibility().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isShowed) {
                if (isShowed) showExtendedFAB();
                else hideExtendedFab();
            }
        });
        binder.getViewModel().observeEdge().observe(this, new Observer<EdgeEnum>() {
            @Override
            public void onChanged(EdgeEnum edge) {
                switch (edge) {
                    case TOP_LEAD:
                        Log.d(TAG,"checkEdge Top Lead");
                        delayTransition(binder.floatingActionButtons.constraintLayout, 250L);
                        constraintSet.clone(binder.floatingActionButtons.constraintLayout);
                        clearConstraints();
                        connectTopConstraints();
                        connectLeadConstraints();
                        constraintSet.applyTo(binder.floatingActionButtons.constraintLayout);

                        //delayTransition(binder.constraintLayout, 250L);
                        //constraintSet.clone(binder.constraintLayout);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.TOP);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.BOTTOM);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.START);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.END);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.TOP, binder.constraintGuidelineHorizontalTop.getId(), ConstraintSet.TOP);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.START, binder.constraintGuidelineVerticalLeading.getId(), ConstraintSet.START);
                        //constraintSet.applyTo(binder.constraintLayout);
                    break;
                    case TOP_TRAIL:
                        Log.d(TAG,"checkEdge Top Trail");
                        delayTransition(binder.floatingActionButtons.constraintLayout, 250L);
                        constraintSet.clone(binder.floatingActionButtons.constraintLayout);
                        clearConstraints();
                        connectTopConstraints();
                        connectTrailConstraints();
                        constraintSet.applyTo(binder.floatingActionButtons.constraintLayout);

                        //delayTransition(binder.constraintLayout, 250L);
                        //constraintSet.clone(binder.constraintLayout);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.TOP);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.BOTTOM);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.START);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.END);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.TOP, binder.constraintGuidelineHorizontalTop.getId(), ConstraintSet.TOP);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.END, binder.constraintGuidelineVerticalTrailing.getId(), ConstraintSet.END);
                        //constraintSet.applyTo(binder.constraintLayout);
                    break;
                    case BOTTOM_LEAD:
                        Log.d(TAG,"checkEdge Bottom Lead");
                        delayTransition(binder.floatingActionButtons.constraintLayout, 250L);
                        constraintSet.clone(binder.floatingActionButtons.constraintLayout);
                        clearConstraints();
                        connectBottomConstraints();
                        connectLeadConstraints();
                        constraintSet.applyTo(binder.floatingActionButtons.constraintLayout);

                        //delayTransition(binder.constraintLayout, 250L);
                        //constraintSet.clone(binder.constraintLayout);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.TOP);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.BOTTOM);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.START);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.END);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.BOTTOM, binder.constraintGuidelineHorizontalBottom.getId(), ConstraintSet.BOTTOM);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.START, binder.constraintGuidelineVerticalLeading.getId(), ConstraintSet.START);
                        //constraintSet.applyTo(binder.constraintLayout);
                    break;
                    case BOTTOM_TRAIL:
                        Log.d(TAG,"checkEdge Bottom Trail");
                        delayTransition(binder.floatingActionButtons.constraintLayout, 250L);
                        constraintSet.clone(binder.floatingActionButtons.constraintLayout);
                        clearConstraints();
                        connectBottomConstraints();
                        connectTrailConstraints();
                        constraintSet.applyTo(binder.floatingActionButtons.constraintLayout);

                        //delayTransition(binder.constraintLayout, 250L);
                        //constraintSet.clone(binder.constraintLayout);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.TOP);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.BOTTOM);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.START);
                        //constraintSet.clear(binder.constraintLayout.getId(),ConstraintSet.END);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.BOTTOM, binder.constraintGuidelineHorizontalBottom.getId(), ConstraintSet.BOTTOM);
                        //constraintSet.connect(binder.constraintLayout.getId(), ConstraintSet.END, binder.constraintGuidelineVerticalTrailing.getId(), ConstraintSet.END);
                        //constraintSet.applyTo(binder.constraintLayout);
                    break;
                    default:

                    break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == binder.floatingActionButtons.floatingActionButtonAndroid) {
            binder.getViewModel().setLiveFABVisibility();
            binder.getViewModel().checkEdge();
        }
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
                binder.getViewModel().checkEdge(layoutParams);
                return true; //Consumed
            case MotionEvent.ACTION_UP:
                float upRawX = motionEvent.getRawX();
                float upRawY = motionEvent.getRawY();

                float upDX = upRawX - binder.getViewModel().getDownRawX();
                float upDY = upRawY - binder.getViewModel().getDownRawY();
                binder.getViewModel().checkEdge(layoutParams);
                return binder.getViewModel().canClick(binder.floatingActionButtons.constraintLayout, upDX, upDY);
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    private void delayTransition(ViewGroup sceneRoot, Long duration) {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(duration); //Delay to animation duration
        TransitionManager.beginDelayedTransition(sceneRoot, transition); //Delay to animate
    }

    private void clearConstraints() {
        constraintSet.clear(binder.floatingActionButtons.floatingActionButtonAndroid.getId(),ConstraintSet.TOP);
        constraintSet.clear(binder.floatingActionButtons.floatingActionButtonAndroid.getId(),ConstraintSet.BOTTOM);
        constraintSet.clear(binder.floatingActionButtons.floatingActionButtonAndroid.getId(),ConstraintSet.START);
        constraintSet.clear(binder.floatingActionButtons.floatingActionButtonAndroid.getId(),ConstraintSet.END);

        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(),ConstraintSet.TOP);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(),ConstraintSet.BOTTOM);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(),ConstraintSet.START);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(),ConstraintSet.END);

        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(),ConstraintSet.TOP);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(),ConstraintSet.BOTTOM);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(),ConstraintSet.START);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(),ConstraintSet.END);

        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(),ConstraintSet.TOP);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(),ConstraintSet.BOTTOM);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(),ConstraintSet.START);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(),ConstraintSet.END);

        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(),ConstraintSet.TOP);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(),ConstraintSet.BOTTOM);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(),ConstraintSet.START);
        constraintSet.clear(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(),ConstraintSet.END);
    }

    private void connectTopConstraints() {
        //constraintSet.setDimensionRatio(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), "1:1");
        constraintSet.connect(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(), ConstraintSet.TOP, binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.TOP, binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.TOP, binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.TOP, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.BOTTOM);
    }

    private void connectBottomConstraints() {
        //constraintSet.setDimensionRatio(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), "1:1");
        constraintSet.connect(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(), ConstraintSet.BOTTOM, binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.TOP);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.BOTTOM, binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.TOP);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.BOTTOM, binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.TOP);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.BOTTOM, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.TOP);
    }

    private void connectLeadConstraints() {
        //constraintSet.setDimensionRatio(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), "1:1");
        constraintSet.connect(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(), ConstraintSet.START, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.START);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.START, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.START);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.START, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.START);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.START, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.START);
    }

    private void connectTrailConstraints() {
        //constraintSet.setDimensionRatio(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), "1:1");
        constraintSet.connect(binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonOne.getId(), ConstraintSet.END, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.END);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonTwo.getId(), ConstraintSet.END, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.END);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonThree.getId(), ConstraintSet.END, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.END);
        constraintSet.connect(binder.floatingActionButtons.extendedFloatingActionButtonFour.getId(), ConstraintSet.END, binder.floatingActionButtons.floatingActionButtonAndroid.getId(), ConstraintSet.END);
    }

    private void showExtendedFAB() {
        binder.floatingActionButtons.floatingActionButtonAndroid.startAnimation(rotationOpen);

        binder.floatingActionButtons.extendedFloatingActionButtonOne.show();
        binder.floatingActionButtons.extendedFloatingActionButtonOne.startAnimation(fromBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonTwo.show();
        binder.floatingActionButtons.extendedFloatingActionButtonTwo.startAnimation(fromBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonThree.show();
        binder.floatingActionButtons.extendedFloatingActionButtonThree.startAnimation(fromBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonFour.show();
        binder.floatingActionButtons.extendedFloatingActionButtonFour.startAnimation(fromBottom);
    }

    private void hideExtendedFab() {
        binder.floatingActionButtons.floatingActionButtonAndroid.startAnimation(rotationClose);

        binder.floatingActionButtons.extendedFloatingActionButtonOne.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonOne.startAnimation(toBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonTwo.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonTwo.startAnimation(toBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonThree.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonThree.startAnimation(toBottom);

        binder.floatingActionButtons.extendedFloatingActionButtonFour.hide();
        binder.floatingActionButtons.extendedFloatingActionButtonFour.startAnimation(toBottom);
    }
}