package com.example.constraintlayoutapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import static androidx.core.util.Preconditions.checkArgument;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout descriptionContainer;
    ImageView descriptionImage;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptionContainer = (ConstraintLayout) findViewById(R.id.descriptionContainer);
        descriptionImage = (ImageView) findViewById(R.id.descriptionImage);
        description = (TextView) findViewById(R.id.description);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setConstraint();
    }

    private void setConstraint() {
        //This is for sample_layout only
        //descriptionContainer
        //descriptionImage
        //description

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(descriptionContainer);
        constraintSet.clear(descriptionImage.getId(),ConstraintSet.START);
        constraintSet.clear(description.getId(),ConstraintSet.END);
        constraintSet.applyTo(descriptionContainer);
    }

    private static boolean isRtl(Context context) {
        checkArgument(context != null, "context must not be null");

        boolean isRtl = false;
        try {
            isRtl = context.getResources().getConfiguration().
                    getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        } catch (NoSuchMethodError e) {
            Log.e(MainActivity.class.getSimpleName(),e.getMessage());
        }
        return isRtl;
    }
}