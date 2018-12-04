package com.bv_gruppe_d.imagej;

public abstract class Filtermatrix {

    protected int matrix[];
    protected double factor;
    protected int hotSpot;

    public Filtermatrix() {

    }

    public abstract void createMatrix();

    public abstract void calculateMatrix();

    public int[] getH() {
        return matrix;
    }

    public double getScalingFactor() {
        return factor;
    }

    public int getDimension() {
        return (int)Math.sqrt(matrix.length);
    }
}
