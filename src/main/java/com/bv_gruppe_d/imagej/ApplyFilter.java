package com.bv_gruppe_d.imagej;

import ij.process.ImageProcessor;

public final class ApplyFilter {
	public static ImageProcessor applyFilterToCopy(ImageProcessor original, Filtermatrix filterMatrix) {
		ImageProcessor copy = original.duplicate();
		applyFilter(original, copy, filterMatrix);
		return copy;
	}

	public static void applyFilterToOriginal(ImageProcessor original, Filtermatrix filterMatrix) {
		applyFilter(original.duplicate(), original, filterMatrix);
	}

	private static void applyFilter(ImageProcessor original, ImageProcessor output,
			Filtermatrix filterMatrix) {
		int size = (int) Math.round(Math.sqrt(filterMatrix.matrix.length));//This assumes a square matrix
		int w = size;
		int h = size;
		double[][] weights = new double[w][h];
		
		//line by line
		for (int y = 0; y < h;++y) {
			for (int x = 0; x < w;++x) {
				weights[x][y] = filterMatrix.matrix[y * w + x];
			}
		}
		int ankerX = filterMatrix.hotSpot % w;
		int ankerY = filterMatrix.hotSpot / w;

		applyFilter(original, output, weights, filterMatrix.factor, ankerX, ankerY);
	}

	/**
	 * The code in the book is really messed up! They made some poor design choices
	 * (ints, anchor in middle, writing to the original). All the border pixels will
	 * remain unchanged
	 */
	private static void applyFilter(ImageProcessor original, ImageProcessor output, double[][] matrix,
			double matrixPreMultiplicator, int anchorX, int anchorY) {
		int w = matrix.length;
		int h = matrix[0].length;

		int maxX = original.getWidth() - w;// off by 1?
		int maxY = original.getHeight() - h;// off by 1?
		
		double minPossibleValue = 0;
		double maxPossibleValue = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				double weight = matrix[j][i];
				double minPixelValue = (weight < 0)? 255 : 0;
				double maxPixelValue = (weight < 0)? 0 : 255;
				minPossibleValue += weight * minPixelValue;
				maxPossibleValue += weight * maxPixelValue;
			}
		}
		minPossibleValue *= matrixPreMultiplicator;
		maxPossibleValue *= matrixPreMultiplicator;

		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				double sum = 0;
				for (int i = 0; i < w; i++) {
					for (int j = 0; j < h; j++) {
						int p = original.getPixel(x + i, y + j);
						sum += matrix[j][i] * p;// switched indices?
					}
				}
				double newValue = matrixPreMultiplicator * sum;
				//newValue = Math.max(0, Math.min(newValue, 255));// clamp
				//Kontrastanpassung
				newValue = (255 * (newValue - minPossibleValue)) / (maxPossibleValue - minPossibleValue);

				output.putPixel(x + anchorX, y + anchorY, (int) Math.round(newValue));
			}
		}
	}

	private ApplyFilter() {
		// Prevent creation of instances
	}
}