package com.example.constraintlayoutapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayoutMain;
    Guideline constraintGuidelineVertical8, constraintGuidelineVertical9, constraintGuidelineHorizontal2, constraintGuidelineHorizontal8, constraintGuidelineHorizontal9;
    View firstView, secondView, thirdView;
    Button btnConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInstanceId();

        btnConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateConstraint();
            }
        });
    }

    private void  setInstanceId() {
        constraintLayoutMain = (ConstraintLayout) findViewById(R.id.constraint_layout_main);
        constraintGuidelineVertical8 = (Guideline) findViewById(R.id.constraint_guideline_vertical_8);
        constraintGuidelineVertical9 = (Guideline) findViewById(R.id.constraint_guideline_vertical_9);
        constraintGuidelineHorizontal2 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_2);
        constraintGuidelineHorizontal8 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_8);
        constraintGuidelineHorizontal9 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_9);
        firstView = (View) findViewById(R.id.first_view);
        secondView = (View) findViewById(R.id.second_view);
        thirdView = (View) findViewById(R.id.third_view);
        btnConstraint = (Button) findViewById(R.id.btnConstraint);
    }

    private void animateConstraint() {
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayoutMain);
        //Clear firstView constraintSet
        constraintSet.clear(firstView.getId(),ConstraintSet.TOP);
        constraintSet.clear(firstView.getId(),ConstraintSet.START);
        constraintSet.clear(firstView.getId(),ConstraintSet.END);
        constraintSet.clear(firstView.getId(),ConstraintSet.BOTTOM);
        //Connect firstView constraintSet
        constraintSet.connect(firstView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(firstView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(firstView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(firstView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal2.getId(), ConstraintSet.BOTTOM);
        //Set Visibility to Invisible secondView constraintSet
        constraintSet.setVisibility(secondView.getId(),ConstraintSet.INVISIBLE);
        //Clear thirdView constraintSet
        constraintSet.clear(thirdView.getId(),ConstraintSet.TOP);
        constraintSet.clear(thirdView.getId(),ConstraintSet.START);
        constraintSet.clear(thirdView.getId(),ConstraintSet.END);
        constraintSet.clear(thirdView.getId(),ConstraintSet.BOTTOM);

        constraintSet.connect(thirdView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal8.getId(), ConstraintSet.TOP);
        constraintSet.connect(thirdView.getId(), ConstraintSet.START, constraintGuidelineVertical8.getId(), ConstraintSet.START);
        constraintSet.connect(thirdView.getId(), ConstraintSet.END, constraintGuidelineVertical9.getId(), ConstraintSet.END);
        constraintSet.connect(thirdView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal9.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayoutMain);
    }


}