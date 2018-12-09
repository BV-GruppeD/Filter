package com.bv_gruppe_d.imagej;

public class Tiefpass_Binomial2 extends Filtermatrix {

    public Tiefpass_Binomial2() {
        createMatrix();
    }

    @Override
    public void createMatrix() {
        int[] _matrix = {1,2,1,2,4,2,1,2,1};
        matrix = _matrix;
        factor = 1.0f/16.0f;
        hotSpot = 4;
    }

    @Override
    public void calculateMatrix() {

    }
}
