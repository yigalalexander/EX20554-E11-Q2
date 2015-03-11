package src;

import java.util.Random;

public class LifeMatrix {

	private boolean _matrix[][];
	private int _width;
	private int _length;
	private int _population;

	/**
	 * @param width Width of the new matrix
	 * @param length Length of the new matrix
	 */
	public LifeMatrix (int width, int length, boolean doRandom){
		_matrix = new boolean[width][length];
		_width=width;
		_length=length;
		_population=0;
		if (doRandom) 
			this.RandomizeMatrix();
	}

	/**
	 * Returns the width of the matrix
	 * @return Integer
	 */
	public int get_width() {
		return _width;
	}
	
	/**
	 * Returns the length of the matrix
	 * @return Integer
	 */
	public int get_length() {
		return _length;
	}


	public void RandomizeMatrix() {
		Random randomGenerator = new Random();
		for (int len=0; len<_length; len++) {
			for (int wid=0; wid<_width; wid++) {
				_matrix[wid][len]=randomGenerator.nextBoolean();
				if (_matrix[wid][len]) 
					_population++;
			}
		}
	}

	/**
	 * Runs a generation of life.
	 * @param count Number of generations to live
	 */
	public void StepGeneration(int count) {
		// init a temp matrix
		LifeMatrix tempMatrix = new LifeMatrix(this.get_width(),this.get_length(),false);
		int livingCount;

		for (int i=0;i<count; i++) {
			//System.out.println("------------ "+i+" ------------");
			for (int posLen=0; posLen<_length; posLen++) {// scan the cells
				for (int posWid=0; posWid<_width; posWid++) {
					livingCount=LivingAround(posLen, posWid); //check the number of neighbors

					if (!_matrix[posWid][posLen]) {
						if (livingCount==3) {
							// no life and 3 Birth?
							tempMatrix.setAlive(posWid,posLen,true);
							//System.out.println("Giving birth at "+posWid+"x"+posLen+"\n");
						} else 
							tempMatrix.setAlive(posWid, posLen, false);

					} else { 

						if (livingCount<2 || livingCount>3) { // life and <2 or >3 Death
							tempMatrix.setAlive(posWid,posLen,false);
							//System.out.println("Killing at "+posWid+"x"+posLen+"\n");
						} else {
							tempMatrix.setAlive(posWid,posLen,true); // life and 2 or 3? exist
							//System.out.println("Sustaining at "+posWid+"x"+posLen+"\n");
						}

					}

				}
			}
			cloneMatrix(tempMatrix); // copy the matrix to this one.
			//System.out.println(this.toString());
		}
	}



	private void cloneMatrix(LifeMatrix source) {

		int maxLength=(_length<source.get_length()?_length:source.get_length());
		int maxWidth=(_width<source.get_width()?_width:source.get_width());
		for (int len=0; len<maxLength; len++) {
			for (int wid=0; wid<maxWidth; wid++) {
				_matrix[wid][len]=source.isAlive(wid,len);
			}
		}

	}

	private boolean isAlive(int wid, int len) {
		if (wid<=_width && len<=_length) 
			return _matrix[wid][len];
		return false;
	}

	private void setAlive(int wid, int len,boolean alive) {

		if (wid<=_width && len<=_length) {
			_matrix[wid][len]=alive;
			if (alive) 
				_population++;
			else
				_population--;
		}
			

	}


	/**
	 * custom toString
	 */
	public String toString() {

		String temp = new String();
		for (int len=0; len<_length; len++) {


			for (int wid=0; wid<_width; wid++) {
				temp+=_matrix[wid][len]==true?"+":"-";

			}
			temp+="\n";
		}
		//temp+= "Population:"+_population+"\n";
		return temp;
	}

	/**
	 * Counts the number of living cells around a given cell
	 * @param row Row of the cell
	 * @param col Column of the cell
	 * @return Count of living cells
	 */
	private int LivingAround(int r,int c) {
		int res=0; //sum of living neighbors 
		int boxSize=1; //how further to look for around each cell

		for (int posLen=(r-boxSize); posLen<=(r+boxSize); posLen++) 
		{

			if ((posLen>=0)&&(posLen<=get_length()-1)) //run the loop only if the row is in range of rows of the matrix
			{ 
				for (int posWid=(c-boxSize); posWid<=(c+boxSize); posWid++) //check all the neighbors in the column
				{ 
					if ((posWid>=0)&&(posWid<=get_width()-1)// if the column is in the range of the matrix, check it
							&& (posWid!=c && posLen!=r)) 	// And is not me
					{  
						res+=_matrix[posWid][posLen]?1:0;
					}
				}
			}

		}
		//System.out.println("At "+r+"x"+c+" there were "+res+" alive\n");
		return res;
	}

}
