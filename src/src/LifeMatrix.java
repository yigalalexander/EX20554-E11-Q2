package src;

import java.util.Random;

public class LifeMatrix {
	
	private boolean _matrix[][];
	private int _width;
	private int _length;
	
	/**
	 * @param width Width of the new matrix
	 * @param length Length of the new matrix
	 */
	public LifeMatrix (int width, int length){
		_matrix = new boolean[width][length];
		_width=width;
		_length=length;
		this.RandomizeMatrix();
	}
	
	public void RandomizeMatrix() {
		Random randomGenerator = new Random();
		for (int len=0; len<_length; len++) {
			for (int wid=0; wid<_width; wid++) {
				_matrix[wid][len]=randomGenerator.nextBoolean();
			}
		}
	}
	
	public void StepGeneration(int count) {
		
	}
	
	public String toString() {
		
		String temp = new String();
		for (int len=0; len<_length; len++) {
			

			for (int wid=0; wid<_width; wid++) {
				temp+=_matrix[wid][len]==true?"+":"-";
				
			}
			temp+="\n";
		}
		
		return temp;
	}

}
