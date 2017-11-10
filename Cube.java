import java.awt.*;
import java.awt.geom.*;
/**
 * This class is just a general class used to define cube.
 * @author Harmanjit Randhawa
 */
public class Cube {
	private int x;
	private int y;
	private int size;
	private int shift;
	/**
	 * Constructor for class cube
	 * @param x1 Initial x-coordinate of the cube 
	 * @param y1 Initial y-coordinate of the cube
	 * @param s Initial width of the face of the cube
	 * @param shift Shift between 2 faces
	 */
	public Cube( int x1, int y1, int s, int shift ){
		x = x1;
		y = y1;
		size = s;
		this.shift = shift; 
	
	}
	/**
	 * Returns the x-coordinate of the cube
	 * @return x-coordinate of the first face of the cube 
	 */
	public int getX(){
		return x;
	}
	/**
	 * Returns the y-coordinate of the cube
	 * @return y-coordinate of the first face of the cube 
	 */
	public int getY(){
		return y;
	}
	/**
	 * Returns the size of the face of the cube
	 * @return size of the face of the cube 
	 */
	public int getSize(){
		return size;
	}
	/**
	 * Returns the shift between the 2 parallel faces  of the cube
	 * @return shift between parallel face of the cube 
	 */
	public int getShift(){
		return shift;
	}

}

