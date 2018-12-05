package com.bv_gruppe_d.imagej;

public abstract class Filtermatrix {

    int matrix[];
    double factor;
    int hotSpot;

    public Filtermatrix() {

    }

    public abstract void createMatrix();

    public abstract void calculateMatrix();
}