package com.bv_gruppe_d.imagej;

public class Tiefpass_Boxfilter extends Filtermatrix {

    public Tiefpass_Boxfilter() {
        createMatrix();
    }

    @Override
    public void createMatrix() {
        int[] _matrix = {1,1,1,1,1,1,1,1,1};
        matrix = _matrix;
        factor = 1.0f/9.0f;
        hotSpot = 4;
    }

    @Override
    public void calculateMatrix() {

    }
}
