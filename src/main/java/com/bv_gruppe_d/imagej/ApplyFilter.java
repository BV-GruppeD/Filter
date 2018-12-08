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
				weights[x][y] = filterMatrix.matrix[y * w + h];
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

		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				double sum = 0;
				for (int i = 0; i < w; i++) {
					for (int j = 0; j < h; j++) {
						int p = original.getPixel(x + i, y + j);
						sum += matrix[j][i] * p;// switched indices?
					}
				}
				int newValue = (int) Math.round(matrixPreMultiplicator * sum);
				newValue = Math.max(0, Math.min(newValue, 255));// clamp

				output.putPixel(x + anchorX, y + anchorY, newValue);
			}
		}
	}

	private ApplyFilter() {
		// Prevent creation of instances
	}
}