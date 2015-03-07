package src;

import javax.swing.JOptionPane;

public class LifeGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Get matrix size
		// Init a new matrix object
		
		
		do { // while the result of confirmdialog is 0 play
			JOptionPane.showMessageDialog(null, "Playing");
		} while (JOptionPane.showConfirmDialog(null, "Do you want to play again?")==0);
		return;
		

	}

}
