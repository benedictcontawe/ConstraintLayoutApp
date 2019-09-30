package com.example.constraintlayoutapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.transition.TransitionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout constraintLayoutMain;
    private ConstraintSet constraintSet;
    private Guideline constraintGuidelineVertical1, constraintGuidelineVertical2, constraintGuidelineVertical4, constraintGuidelineVertical5, constraintGuidelineVertical6, constraintGuidelineVertical8, constraintGuidelineVertical9;
    private Guideline constraintGuidelineHorizontal1, constraintGuidelineHorizontal2, constraintGuidelineHorizontal4, constraintGuidelineHorizontal5, constraintGuidelineHorizontal6, constraintGuidelineHorizontal8, constraintGuidelineHorizontal9;
    private View firstView, secondView, thirdView, forthView, fifthView;
    private Button btnContract, btnExpand, btnScatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void  initialize() {
        constraintSet = new ConstraintSet();
        constraintLayoutMain = (ConstraintLayout) findViewById(R.id.constraint_layout_main);
        constraintGuidelineVertical1 = (Guideline) findViewById(R.id.constraint_guideline_vertical_1);
        constraintGuidelineVertical2 = (Guideline) findViewById(R.id.constraint_guideline_vertical_2);
        constraintGuidelineVertical4 = (Guideline) findViewById(R.id.constraint_guideline_vertical_4);
        constraintGuidelineVertical5 = (Guideline) findViewById(R.id.constraint_guideline_vertical_5);
        constraintGuidelineVertical6 = (Guideline) findViewById(R.id.constraint_guideline_vertical_6);
        constraintGuidelineVertical8 = (Guideline) findViewById(R.id.constraint_guideline_vertical_8);
        constraintGuidelineVertical9 = (Guideline) findViewById(R.id.constraint_guideline_vertical_9);
        constraintGuidelineHorizontal1 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_1);
        constraintGuidelineHorizontal2 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_2);
        constraintGuidelineHorizontal4 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_4);
        constraintGuidelineHorizontal5 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_5);
        constraintGuidelineHorizontal6 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_6);
        constraintGuidelineHorizontal8 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_8);
        constraintGuidelineHorizontal9 = (Guideline) findViewById(R.id.constraint_guideline_horizontal_9);
        firstView = (View) findViewById(R.id.first_view);
        secondView = (View) findViewById(R.id.second_view);
        thirdView = (View) findViewById(R.id.third_view);
        forthView = (View) findViewById(R.id.forth_view);
        fifthView = (View) findViewById(R.id.fifth_view);
        btnContract = (Button) findViewById(R.id.btnContract);
        btnExpand = (Button) findViewById(R.id.btnExpand);
        btnScatter = (Button) findViewById(R.id.btnScatter);

        btnContract.setOnClickListener(this);
        btnExpand.setOnClickListener(this);
        btnScatter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContract:
                animateConstraintContract();
                break;
            case R.id.btnExpand:
                animateConstraintExpand();
                break;
            case R.id.btnScatter:
                animateConstraintScatter();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }


    }

    private void animateConstraintExpand() {
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        constraintSet.clone(constraintLayoutMain);
        clearFirst();
        //Connect firstView constraintSet
        constraintSet.connect(firstView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal1.getId(), ConstraintSet.TOP);
        constraintSet.connect(firstView.getId(), ConstraintSet.START, constraintGuidelineVertical1.getId(), ConstraintSet.START);
        constraintSet.connect(firstView.getId(), ConstraintSet.END, constraintGuidelineVertical9.getId(), ConstraintSet.END);
        constraintSet.connect(firstView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal2.getId(), ConstraintSet.BOTTOM);
        clearSecond();
        //Connect secondView constraintSet
        constraintSet.connect(secondView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal1.getId(), ConstraintSet.TOP);
        constraintSet.connect(secondView.getId(), ConstraintSet.START, constraintGuidelineVertical1.getId(), ConstraintSet.START);
        constraintSet.connect(secondView.getId(), ConstraintSet.END, constraintGuidelineVertical2.getId(), ConstraintSet.END);
        constraintSet.connect(secondView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal9.getId(), ConstraintSet.BOTTOM);
        clearThird();
        //Connect thirdView constraintSet
        constraintSet.connect(thirdView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal1.getId(), ConstraintSet.TOP);
        constraintSet.connect(thirdView.getId(), ConstraintSet.START, constraintGuidelineVertical8.getId(), ConstraintSet.START);
        constraintSet.connect(thirdView.getId(), ConstraintSet.END, constraintGuidelineVertical9.getId(), ConstraintSet.END);
        constraintSet.connect(thirdView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal9.getId(), ConstraintSet.BOTTOM);
        clearForth();
        //Connect forthView constraintSet
        constraintSet.connect(forthView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal8.getId(), ConstraintSet.TOP);
        constraintSet.connect(forthView.getId(), ConstraintSet.START, constraintGuidelineVertical1.getId(), ConstraintSet.START);
        constraintSet.connect(forthView.getId(), ConstraintSet.END, constraintGuidelineVertical9.getId(), ConstraintSet.END);
        constraintSet.connect(forthView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal9.getId(), ConstraintSet.BOTTOM);
        //Set Visibility to visible fifthView constraintSet
        constraintSet.setVisibility(fifthView.getId(),ConstraintSet.VISIBLE);
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraintLayoutMain);
    }

    private void animateConstraintContract() {
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        constraintSet.clone(constraintLayoutMain);
        clearFirst();
        //Connect firstView constraintSet
        constraintSet.connect(firstView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal4.getId(), ConstraintSet.TOP);
        constraintSet.connect(firstView.getId(), ConstraintSet.START, constraintGuidelineVertical4.getId(), ConstraintSet.START);
        constraintSet.connect(firstView.getId(), ConstraintSet.END, constraintGuidelineVertical6.getId(), ConstraintSet.END);
        constraintSet.connect(firstView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal5.getId(), ConstraintSet.BOTTOM);
        clearSecond();
        //Connect secondView constraintSet
        constraintSet.connect(secondView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal4.getId(), ConstraintSet.TOP);
        constraintSet.connect(secondView.getId(), ConstraintSet.START, constraintGuidelineVertical4.getId(), ConstraintSet.START);
        constraintSet.connect(secondView.getId(), ConstraintSet.END, constraintGuidelineVertical5.getId(), ConstraintSet.END);
        constraintSet.connect(secondView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal6.getId(), ConstraintSet.BOTTOM);
        clearThird();
        //Connect thirdView constraintSet
        constraintSet.connect(thirdView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal4.getId(), ConstraintSet.TOP);
        constraintSet.connect(thirdView.getId(), ConstraintSet.START, constraintGuidelineVertical5.getId(), ConstraintSet.START);
        constraintSet.connect(thirdView.getId(), ConstraintSet.END, constraintGuidelineVertical6.getId(), ConstraintSet.END);
        constraintSet.connect(thirdView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal6.getId(), ConstraintSet.BOTTOM);
        clearForth();
        //Connect forthView constraintSet
        constraintSet.connect(forthView.getId(), ConstraintSet.TOP, constraintGuidelineHorizontal5.getId(), ConstraintSet.TOP);
        constraintSet.connect(forthView.getId(), ConstraintSet.START, constraintGuidelineVertical4.getId(), ConstraintSet.START);
        constraintSet.connect(forthView.getId(), ConstraintSet.END, constraintGuidelineVertical6.getId(), ConstraintSet.END);
        constraintSet.connect(forthView.getId(), ConstraintSet.BOTTOM, constraintGuidelineHorizontal6.getId(), ConstraintSet.BOTTOM);
        //Set Visibility to visible fifthView constraintSet
        constraintSet.setVisibility(fifthView.getId(),ConstraintSet.VISIBLE);
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraintLayoutMain);
    }

    private void animateConstraintScatter() {
        TransitionManager.beginDelayedTransition(constraintLayoutMain);
        constraintSet.clone(constraintLayoutMain);
        clearFirst();
        //Connect firstView constraintSet
        constraintSet.connect(firstView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        clearSecond();
        //Connect secondView constraintSet
        constraintSet.connect(secondView.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        clearThird();
        //Connect thirdView constraintSet
        constraintSet.connect(thirdView.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        clearForth();
        //Connect forthView constraintSet
        constraintSet.connect(forthView.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        //Set Visibility to invisible fifthView constraintSet
        constraintSet.setVisibility(fifthView.getId(),ConstraintSet.INVISIBLE);
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraintLayoutMain);
    }

    private void clearFirst() {
        //Clear firstView constraintSet
        constraintSet.clear(firstView.getId(),ConstraintSet.TOP);
        constraintSet.clear(firstView.getId(),ConstraintSet.START);
        constraintSet.clear(firstView.getId(),ConstraintSet.END);
        constraintSet.clear(firstView.getId(),ConstraintSet.BOTTOM);
    }

    private void clearSecond() {
        //Clear secondView constraintSet
        constraintSet.clear(secondView.getId(),ConstraintSet.TOP);
        constraintSet.clear(secondView.getId(),ConstraintSet.END);
        constraintSet.clear(secondView.getId(),ConstraintSet.START);
        constraintSet.clear(secondView.getId(),ConstraintSet.BOTTOM);
    }

    private void clearThird() {
        //Clear thirdView constraintSet
        constraintSet.clear(thirdView.getId(),ConstraintSet.TOP);
        constraintSet.clear(thirdView.getId(),ConstraintSet.END);
        constraintSet.clear(thirdView.getId(),ConstraintSet.START);
        constraintSet.clear(thirdView.getId(),ConstraintSet.BOTTOM);
    }

    private void clearForth() {
        //Clear forthView constraintSet
        constraintSet.clear(forthView.getId(),ConstraintSet.TOP);
        constraintSet.clear(forthView.getId(),ConstraintSet.END);
        constraintSet.clear(forthView.getId(),ConstraintSet.START);
        constraintSet.clear(forthView.getId(),ConstraintSet.BOTTOM);
    }

}