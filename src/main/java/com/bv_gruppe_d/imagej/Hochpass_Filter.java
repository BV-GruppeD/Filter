package com.bv_gruppe_d.imagej;

public class Hochpass_Filter extends Filtermatrix {
		
		public void Hochpass() {
			createMatrix();
		}

		@Override
		public void createMatrix() {
			
		}

		@Override
		public void calculateMatrix() {
			Tiefpass_Binomial4 binomialTiefpassVierterOrdnung = new Tiefpass_Binomial4();
			int tpmatrix[] = binomialTiefpassVierterOrdnung.getH();
			double factor = binomialTiefpassVierterOrdnung.getScalingFactor();
			
			int L = tpmatrix.length;
			
			for (int i = 0; i<L;i++){
					tpmatrix[i] = -tpmatrix[i];
			}
			tpmatrix[(L+1)/2] = (int)(1/factor)-tpmatrix[(L+1)/2];
			matrix = tpmatrix;
		}		
}
