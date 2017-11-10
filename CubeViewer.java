import java.awt.Color;

import javax.swing.JFrame;

/**
 * This class is used to view the cube onto the jframe
 * @author Harmanjit Randhawa
 *
 */

public class CubeViewer {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize( 700,700 );
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle(" MOVE THE CUBE  ");
		CubeComponent RDC = new CubeComponent ( 300, 300, 50, 20 );
		frame.add(RDC);
		frame.setVisible(true );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
