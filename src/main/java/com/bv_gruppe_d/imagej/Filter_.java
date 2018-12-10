package com.bv_gruppe_d.imagej;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Filter_ implements PlugInFilter {

	private static final String[] choices = { "Boxfilter", "Binomialfilter 3x3", "Binomialfilter 5x5",
			"Hochpassfilter" };

	@Override
	public void run(ImageProcessor ip) {
		GenericDialog dialog = createChoicesDialog(choices);

		Filtermatrix filtermatrix = getDemandedFilter(dialog);
		if (filtermatrix != null) {
			ApplyFilter.applyFilterToOriginal(ip, filtermatrix);
		}
	}

	private GenericDialog createChoicesDialog(String[] choices) {
		GenericDialog dialog = new GenericDialog("Wähle eine Filtermethode");
		dialog.addChoice("Filtermethode:", choices, "");
		dialog.showDialog();
		return dialog;
	}

	private Filtermatrix getDemandedFilter(GenericDialog dialog) {
		Filtermatrix matrix = null;

		switch (dialog.getNextChoice()) {
		case "Boxfilter":
			matrix = new Tiefpass_Boxfilter();
			break;

		case "Binomialfilter 3x3":
			matrix = new Tiefpass_Binomial2();
			break;

		case "Binomialfilter 5x5":
			matrix = new Tiefpass_Binomial4();
			break;

		case "Hochpassfilter":
			matrix = new Hochpass_Filter();
			break;

		default:
			break;
		}

		return matrix;
	}

	@Override
	public int setup(String arg0, ImagePlus img) {
		return DOES_8G;
	}
}
