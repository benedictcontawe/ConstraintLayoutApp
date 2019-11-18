package com.example.constraintlayoutapp

import android.os.Bundle
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.layout_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var constraintSet: ConstraintSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        constraintSet = ConstraintSet()

        btnContract.setOnClickListener(this)
        btnExpand.setOnClickListener(this)
        btnScatter.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnContract -> animateConstraintContract()
            R.id.btnExpand -> animateConstraintExpand()
            R.id.btnScatter -> animateConstraintScatter()
            else -> throw IllegalStateException("Unexpected value: " + view.id) as Throwable
        }
    }

    private fun animateConstraintExpand() {
        TransitionManager.beginDelayedTransition(constraint_layout_main!!) //Delay to animate
        constraintSet.clone(constraint_layout_main!!) //Clone first constraint set of the parent must be Constraint Layout
        clearFirst()
        //Connect first_view constraintSet
        constraintSet.connect(first_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_1.id, ConstraintSet.TOP)
        constraintSet.connect(first_view.id, ConstraintSet.START, constraint_guideline_vertical_1.id, ConstraintSet.START)
        constraintSet.connect(first_view.id, ConstraintSet.END, constraint_guideline_vertical_9.id, ConstraintSet.END)
        constraintSet.connect(first_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_2.id, ConstraintSet.BOTTOM)
        clearSecond()
        //Connect second_view constraintSet
        constraintSet.connect(second_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_1.id, ConstraintSet.TOP)
        constraintSet.connect(second_view.id, ConstraintSet.START, constraint_guideline_vertical_1.id, ConstraintSet.START)
        constraintSet.connect(second_view.id, ConstraintSet.END, constraint_guideline_vertical_2.id, ConstraintSet.END)
        constraintSet.connect(second_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_9.id, ConstraintSet.BOTTOM)
        clearThird()
        //Connect third_view constraintSet
        constraintSet.connect(third_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_1.id, ConstraintSet.TOP)
        constraintSet.connect(third_view.id, ConstraintSet.START, constraint_guideline_vertical_8.id, ConstraintSet.START)
        constraintSet.connect(third_view.id, ConstraintSet.END, constraint_guideline_vertical_9.id, ConstraintSet.END)
        constraintSet.connect(third_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_9.id, ConstraintSet.BOTTOM)
        clearForth()
        //Connect forth_view constraintSet
        constraintSet.connect(forth_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_8.id, ConstraintSet.TOP)
        constraintSet.connect(forth_view.id, ConstraintSet.START, constraint_guideline_vertical_1.id, ConstraintSet.START)
        constraintSet.connect(forth_view.id, ConstraintSet.END, constraint_guideline_vertical_9.id, ConstraintSet.END)
        constraintSet.connect(forth_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_9.id, ConstraintSet.BOTTOM)
        //Set Visibility to visible fifth_view constraintSet
        constraintSet.setVisibility(fifth_view.id, ConstraintSet.VISIBLE)
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraint_layout_main!!)
    }

    private fun animateConstraintContract() {
        TransitionManager.beginDelayedTransition(constraint_layout_main!!) //Delay to animate
        constraintSet.clone(constraint_layout_main!!) //Clone first constraint set of the parent must be Constraint Layout
        clearFirst()
        //Connect first_view constraintSet
        constraintSet.connect(first_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_4.id, ConstraintSet.TOP)
        constraintSet.connect(first_view.id, ConstraintSet.START, constraint_guideline_vertical_4.id, ConstraintSet.START)
        constraintSet.connect(first_view.id, ConstraintSet.END, constraint_guideline_vertical_6.id, ConstraintSet.END)
        constraintSet.connect(first_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_5.id, ConstraintSet.BOTTOM)
        clearSecond()
        //Connect second_view constraintSet
        constraintSet.connect(second_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_4.id, ConstraintSet.TOP)
        constraintSet.connect(second_view.id, ConstraintSet.START, constraint_guideline_vertical_4.id, ConstraintSet.START)
        constraintSet.connect(second_view.id, ConstraintSet.END, constraint_guideline_vertical_5.id, ConstraintSet.END)
        constraintSet.connect(second_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_6.id, ConstraintSet.BOTTOM)
        clearThird()
        //Connect third_view constraintSet
        constraintSet.connect(third_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_4.id, ConstraintSet.TOP)
        constraintSet.connect(third_view.id, ConstraintSet.START, constraint_guideline_vertical_5.id, ConstraintSet.START)
        constraintSet.connect(third_view.id, ConstraintSet.END, constraint_guideline_vertical_6.id, ConstraintSet.END)
        constraintSet.connect(third_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_6.id, ConstraintSet.BOTTOM)
        clearForth()
        //Connect forth_view constraintSet
        constraintSet.connect(forth_view.id, ConstraintSet.TOP, constraint_guideline_horizontal_5.id, ConstraintSet.TOP)
        constraintSet.connect(forth_view.id, ConstraintSet.START, constraint_guideline_vertical_4.id, ConstraintSet.START)
        constraintSet.connect(forth_view.id, ConstraintSet.END, constraint_guideline_vertical_6.id, ConstraintSet.END)
        constraintSet.connect(forth_view.id, ConstraintSet.BOTTOM, constraint_guideline_horizontal_6.id, ConstraintSet.BOTTOM)
        //Set Visibility to visible fifth_view constraintSet
        constraintSet.setVisibility(fifth_view.id, ConstraintSet.VISIBLE)
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraint_layout_main!!)
    }

    private fun animateConstraintScatter() {
        TransitionManager.beginDelayedTransition(constraint_layout_main!!) //Delay to animate
        constraintSet.clone(constraint_layout_main!!) //Clone first constraint set of the parent must be Constraint Layout
        clearFirst()
        //Connect first_view constraintSet -> app:layout_constraintTop_toTopOf="parent"
        constraintSet.connect(first_view.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        clearSecond()
        //Connect second_view constraintSet -> app:layout_constraintStart_toStartOf="parent"
        constraintSet.connect(second_view.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        clearThird()
        //Connect third_view constraintSet -> app:layout_constraintEnd_toEndOf="parent"
        constraintSet.connect(third_view.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        clearForth()
        //Connect forth_view constraintSet -> app:layout_constraintBottom_toBottomOf="parent"
        constraintSet.connect(forth_view.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        //Set Visibility to invisible fifth_view constraintSet -> android:visibility="invisible"
        constraintSet.setVisibility(fifth_view.id, ConstraintSet.INVISIBLE)
        //Apply Constraint Set to Constraint Layout
        constraintSet.applyTo(constraint_layout_main!!)
    }

    private fun clearFirst() {
        //Clear first_view constraintSet
        constraintSet.clear(first_view.id, ConstraintSet.TOP) //app:layout_constraintTop_toTopOf="@null"
        constraintSet.clear(first_view.id, ConstraintSet.START) //app:layout_constraintStart_toStartOf="@null"
        constraintSet.clear(first_view.id, ConstraintSet.END) //app:layout_constraintEnd_toEndOf="@null"
        constraintSet.clear(first_view.id, ConstraintSet.BOTTOM) //app:layout_constraintBottom_toBottomOf="@null"
    }

    private fun clearSecond() {
        //Clear second_view constraintSet
        constraintSet.clear(second_view.id, ConstraintSet.TOP) //app:layout_constraintTop_toTopOf="@null"
        constraintSet.clear(second_view.id, ConstraintSet.END) //app:layout_constraintStart_toStartOf="@null"
        constraintSet.clear(second_view.id, ConstraintSet.START) //app:layout_constraintEnd_toEndOf="@null"
        constraintSet.clear(second_view.id, ConstraintSet.BOTTOM) //app:layout_constraintBottom_toBottomOf="@null"
    }

    private fun clearThird() {
        //Clear third_view constraintSet
        constraintSet.clear(third_view.id, ConstraintSet.TOP) //app:layout_constraintTop_toTopOf="@null"
        constraintSet.clear(third_view.id, ConstraintSet.END) //app:layout_constraintStart_toStartOf="@null"
        constraintSet.clear(third_view.id, ConstraintSet.START) //app:layout_constraintEnd_toEndOf="@null"
        constraintSet.clear(third_view.id, ConstraintSet.BOTTOM) //app:layout_constraintBottom_toBottomOf="@null"
    }

    private fun clearForth() {
        //Clear forth_view constraintSet
        constraintSet.clear(forth_view.id, ConstraintSet.TOP) //app:layout_constraintTop_toTopOf="@null"
        constraintSet.clear(forth_view.id, ConstraintSet.END) //app:layout_constraintStart_toStartOf="@null"
        constraintSet.clear(forth_view.id, ConstraintSet.START) //app:layout_constraintEnd_toEndOf="@null"
        constraintSet.clear(forth_view.id, ConstraintSet.BOTTOM) //app:layout_constraintBottom_toBottomOf="@null"
    }

}