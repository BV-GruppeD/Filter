package com.bv_gruppe_d.imagej;

public class Tiefpass_Binomial4 extends Filtermatrix {

    public Tiefpass_Binomial4() {
        createMatrix();
    }

    @Override
    public void createMatrix() {
        int[] _matrix = {1,4,6,4,1,4,16,24,16,4,6,24,36,24,6,4,16,24,16,4,1,4,6,4,1};
        matrix = _matrix;
        factor = 1/256;
        hotSpot = 12;
    }

    @Override
    public void calculateMatrix() {

    }
}
