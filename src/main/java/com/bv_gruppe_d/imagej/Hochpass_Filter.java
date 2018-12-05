package com.bv_gruppe_d.imagej;

public class Hochpass_Filter {
	
		int matrix[][];
		double factor;
		
		public void Hochpass() {
			int L = matrix[0].length;
			int H = matrix.length;
			
			for (int i = 0; i<L;i++){
				for (int j = 0; j<H;j++) {
					matrix[i][j] = -matrix[i][j];
				} 				
			}
			matrix[L/2][H/2] = (int)(1/factor)-matrix[L/2][H/2];
		}
		
}
