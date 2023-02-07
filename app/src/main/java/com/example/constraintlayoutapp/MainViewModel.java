package com.example.constraintlayoutapp;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private final static float CLICK_DRAG_TOLERANCE = 10;
    private float downRawX, downRawY, dX, dY, newX, newY;
    private int viewWidth, viewHeight, parentWidth, parentHeight;
    private final MutableLiveData<Boolean> liveFABVisibility;
    private final MutableLiveData<EdgeEnum> liveEdge;

    public MainViewModel() {
        liveFABVisibility = new MutableLiveData<Boolean>(false);
        liveEdge = new MutableLiveData<EdgeEnum>(EdgeEnum.BOTTOM_LEAD);
    }

    public void setLiveFABVisibility() {
        if (liveFABVisibility.getValue() != null) setLiveFABVisibility(!liveFABVisibility.getValue());
        else setLiveFABVisibility(false);
    }

    private void setLiveFABVisibility(boolean isShowed) {
        liveFABVisibility.setValue(isShowed);
    }

    public LiveData<Boolean> observeFABVisibility() {
        return liveFABVisibility;
    }

    public LiveData<EdgeEnum> observeEdge() {
        return liveEdge;
    }

    public void setDownRawX(float downRawX) {
        this.downRawX = downRawX;
    }

    public void setDownRawY(float downRawY) {
        this.downRawY = downRawY;
    }

    public void setDx(float dX) {
        this.dX = dX;
    }

    public void setDy(float dY) {
        this.dY = dY;
    }

    public void setNewX(ViewGroup.MarginLayoutParams layoutParams) {
        if (newX > ((parentWidth - viewWidth - layoutParams.rightMargin) / 2)) {
            newX = parentWidth - viewWidth - layoutParams.rightMargin;
        } else {
            newX = layoutParams.leftMargin;
        }
    }

    public void setNewX(float rawX, ViewGroup.MarginLayoutParams layoutParams) {
        this.newX = rawX + dX;
        newX = Math.max(layoutParams.leftMargin, newX); // Don't allow the FAB past the left hand side of the parent
        newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX); // Don't allow the FAB past the right hand side of the parent
    }

    public void setNewY(float rawY, ViewGroup.MarginLayoutParams layoutParams) {
        newY = rawY + dY;
        newY = Math.max(layoutParams.topMargin, newY); // Don't allow the FAB past the top of the parent
        newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin, newY); // Don't allow the FAB past the bottom of the parent
    }


    public void setViewDimension(View view) {
        this.viewWidth = view.getWidth();
        this.viewHeight = view.getHeight();
    }

    public void setParentDimension(ViewParent viewParent) {
        this.parentWidth = ( (View) viewParent ).getWidth();
        this.parentHeight = ( (View) viewParent ).getHeight();
    }

    public float getDownRawX() {
        return downRawX;
    }

    public float getDownRawY() {
        return downRawY;
    }

    public float getNewX() {
        return newX;
    }

    public float getNewY() {
        return newY;
    }

    public void checkEdge() {
        liveEdge.setValue(liveEdge.getValue());
    }

    public void checkEdge(ViewGroup.MarginLayoutParams layoutParams) {
        boolean isBottom, isTrail;
        isBottom = newY > ((parentHeight - viewHeight - layoutParams.bottomMargin) / 2);
        isTrail = newX > ((parentWidth - viewWidth - layoutParams.rightMargin) / 2);
        if (!isBottom && !isTrail)  liveEdge.setValue(EdgeEnum.TOP_LEAD);
        else if (!isBottom && isTrail)  liveEdge.setValue(EdgeEnum.TOP_TRAIL);
        else if (isBottom && !isTrail)  liveEdge.setValue(EdgeEnum.BOTTOM_LEAD);
        else if (isBottom && isTrail) liveEdge.setValue(EdgeEnum.BOTTOM_TRAIL);
    }

    private void setRippleEffect(View view, long delayMillis) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setPressed(true);
            }
        },delayMillis);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setPressed(false);
            }
        },delayMillis);
    }

    private boolean canClick(float upDX, float upDY) {
        return Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE;
    }

    public boolean canClick(View view, float upDX, float upDY) {
        if (canClick(upDX, upDY)) {
            setRippleEffect(view, 250);
            return view.performClick();
        } else {
            setRippleEffect(view, 250);
            return true; //Consumed
        }
    }
}