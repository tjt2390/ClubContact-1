package com.example.eventstrackerapp.ui.home.animations;

import android.view.animation.Interpolator;

public class MyBounceInterpolator implements Interpolator {
    /** The higher the value on the amplitude, the more pronounced bounces */
    private double mAmplitude = 1;

    /** The higher the value on the frequency, the more wobbles occur during the animation */
    private double mFrequency = 10;

    public MyBounceInterpolator(double amplitude, double frequency){
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    /**
     * This method uses the mathematical function to create the bounce effect.
     * It is called by the system to draw each frame of the animation.
     *
     *          f(t) = -[e^(t/a) * cos(t * w) + 1]
     *
     * @param input the time
     * @return the equation that creates the bounce effect
     */
    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, -input/mAmplitude) *
                                Math.cos(mFrequency * input) + 1);
    }
}
