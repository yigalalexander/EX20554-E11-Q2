package src;

import javax.swing.JOptionPane;

public class LifeGame {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String buffer;
		int width, length, userChoice;
		LifeMatrix earth;
		String[] buttons = { "Continue Game", "Exit", "Wipe all life" };
		

		// Get matrix size
		buffer=JOptionPane.showInputDialog(null, "Enter the width of the matrix");
		width=Integer.parseInt(buffer);
		buffer=JOptionPane.showInputDialog(null, "Enter the length of the matrix");
		length=Integer.parseInt(buffer);
		
		// Init a new matrix object
		earth = new LifeMatrix(width, length,true);
		
		do { // while the result of confirmdialog is 0 play
			JOptionPane.showMessageDialog(null, earth.toString());
			
			userChoice=JOptionPane.showOptionDialog(null, "What do you want to do?", "LifeGame",
			        JOptionPane.PLAIN_MESSAGE, 0, null, buttons, buttons[2]);
			if (userChoice==2) {
				earth.RandomizeMatrix();
			} else if (userChoice==0) {
				earth.StepGeneration(1);
			}			
		} while (userChoice!=1);
		return;
		

	}

}
