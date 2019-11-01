package com.example.eventstrackerapp.ui.home.animations;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.eventstrackerapp.R;

public class MyRotateInterpolator{
    Animation rotate;
    Button button;
    View view;

    public MyRotateInterpolator(View view, Button button){
        rotate = AnimationUtils.loadAnimation(view.getContext(), R.anim.btn_rotate);
        this.button = button;
        this.view = view;
    }

    public void rotateAnimation(){
        button.startAnimation(rotate);
    }
}
