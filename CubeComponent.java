import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;

import javax.swing.*;
public class CubeComponent extends JComponent{
	private Cube cube;
	private double x;
	private double y;
	private int size;
	private double shift;
	private Point2D.Double[] rect1Array = new Point2D.Double[4];
	private Point2D.Double[] rect2Array = new Point2D.Double[4];
	private Rectangle2D.Double rect1;
	private Rectangle2D.Double rect2;
	private final int  MOVE_BY =5;
	
	private Point2D.Double vertex = new Point2D.Double();
	private Point2D.Double mousePointer = new Point2D.Double();
	
	/**
	 * This is the constructor for component class that is used to draw the cube
	 * and change position of the cube on the jframe using mouse and keyboard
	 * @param x1 Initial x-cordinate of the cube 
	 * @param y1 Initial y-cordinate of the cube
	 * @param size Initial width of the face of the cube
	 * @param shift Shift between 2 faces
	 */
	public CubeComponent( int x1, int y1, int size, int shift ){
		x = x1;
		y = y1;
		this.size = size;
		this.shift = shift; 
		makeRect(x,y);
		cube = new Cube( x1,y1,size,shift);
		addKeyListener(new ArrowKeyListener());
		addMouseListener( new MousePressListener());
		setFocusable( true );
		requestFocus();
	}
	/**
	 * This method makes the 2 faces of the cube and join those 2 faces with set of parrallel lines stored in an array
	 * @param x X-coordiante of the first face of the cube
	 * @param y Y-coordiante of the first face of the cube
	 */
	public void makeRect( double x, double y ){
		rect1 = new Rectangle2D.Double((int)x,(int)y,size, size);
		rect2 = new Rectangle2D.Double ((int) (x+shift),(int) (y+ shift), size, size);
		rect1Array = makeRectArray( x,y ,rect1Array, 0);
		rect2Array = makeRectArray(x,y,rect2Array, shift);	
	}
	/**
	 * This method makes an array that stores 4 points of the face. It is used to draw lines between faces of the cube
	 * @param x1 X-coordiante of the face of the cube
	 * @param y1 Y-coordiante of the face of the cube
	 * @param rect Array in which data is stored
	 * @param shift shift between 2 faces of the cube 
	 * @return An array that has 4 points of the face 
	 */
	public  Point2D.Double[] makeRectArray(double x1, double y1, Point2D.Double[] rect, double shift){
		x1 = x1 + shift;
		y1 = y1 +shift;
		rect[0] = new Point2D.Double( x1,y1 );
		rect[1] = new Point2D.Double ( x1+ size, y1);
		rect[2] = new Point2D.Double ( x1+size, y1+size);
		rect[3] = new Point2D.Double( x1,y1+size);
		return rect;
	}
	/**
	 * Overriden method that is used to draw the components onto the jframe
	 * @param g It is the graphics parameter used to draw the components 
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = ( Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.draw(rect1);
		g2.draw(rect2);
		for ( int i =0; i< 4; i++){//draw the lines that join 2 squares
			g2.draw(new Line2D.Double(rect1Array[i], rect2Array[i]));
		}
	}
	/**
	 * It finds the closest vertex to the param x,y and returns that point
	 * @param x x-coordinate from where the vertex is to be found
	 * @param y y-coordinate from where the vertex is to be found
	 * @return Closest vertex from x,y
	 */
	public Point2D.Double findClosestVertex( double x, double y ){
		// it compares distance with all 8 vertices of the cube and finds the minimum
		Point2D.Double point  = rect1Array[0];
		Point2D.Double temp = new Point2D.Double(x,y);
		double min = 1000;
		for ( Point2D.Double p : rect1Array ){
			if ( p.distance(temp) < min ){
			point = p;
			min = p.distance(temp);
			}
		}
		for ( Point2D.Double p : rect2Array ){
			if ( p.distance(temp) < min ){
			point = p;
			min = p.distance(temp);
			}
		}
		return point;
	}
	/**
	 * This method is used to move the cube down when a downward arrow is pressed
	 */
	private void moveDown() {
		if ( y <= getHeight() - size - MOVE_BY - shift){
			y += MOVE_BY;
			moveTo(x , y );
		}
	}
	/**
	 * This method is used to move the cube up when a upward arrow is pressed
	 */
	private void moveUp() {
		if ( y >= MOVE_BY ){
			y -= MOVE_BY;	
			moveTo( x,y );
		}
	}
	/**
	 * This method is used to move the cube right when a right arrow is pressed
	 */
	private void moveRight() {
		if ( x <= getWidth() - size - MOVE_BY- shift ){
			x += MOVE_BY;
			moveTo( x, y );
		}
	}
	/**
	 * This method is used to move the cube left when a left arrow is pressed
	 */
	private void moveLeft() {
		if ( x >= MOVE_BY ){//if it is more than 5 only then move the component otherwise it will get out of the bounds  
			x -= MOVE_BY;
			moveTo( x,y );
		}
	}
	/**
	 * This method is used to move the cube to x1,y1 location 
	 * @param x1 x-coordinate of the point where the cube should be moved 
	 * @param y1 y-coordinate of the point where the cube should be moved
	 */
	public void moveTo( double x1, double y1 ){
		this.x = x1;
		this.y = y1;
		cube = new Cube( (int)x1, (int)y1, size, (int)shift );
		makeRect(x1,y1);
		repaint();
	}
	/**
	 * This class is used to change the position of the cube using key board.
	 * It implements the KeyListener interface 
	 */
	private class ArrowKeyListener implements KeyListener{
		/**
		 * This method is called when ever a key is pressed on keyboard. It first determines the code of the key and then
		 * changes the position of the cube by calling the appropriate function
		 * @param event This is the event that happens when a key is pressed 
		 */
		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();
			if ( key == KeyEvent.VK_LEFT )
				moveLeft();
			if ( key == KeyEvent.VK_RIGHT )
				moveRight();
			if ( key == KeyEvent.VK_UP )
				moveUp();
			if ( key == KeyEvent.VK_DOWN )
				moveDown();
		}
		/**
		 * It is an abstract method in the KeyListener class
		 * @param event This is the event that happens when a key is released 
		 */
		public void keyReleased(KeyEvent event) {
			// TODO Auto-generated method stub
		}
		/**
		 * It is an abstract method in the KeyListener class
		 * @param event This is the event that happens when a key is pressed 
		 */
		public void keyTyped(KeyEvent event) {
			// TODO Auto-generated method stub
		}
	}//class ends
	
	/**
	 * This class is used to change the position of the cube using mouse.
	 * It implements the MouseListener class
	 * @author Harman Randhawa
	 *
	 */
	private class MousePressListener implements MouseListener , MouseMotionListener{
		private boolean drag = false;
		/**
		 * This method changes the position of the cube
		 * @param e This is the event that happens when a key is pressed on the keyboard
		 */
		public void mousePressed(MouseEvent e) {
			double xC = e.getX();
			double yC = e.getY();
			mousePointer = new Point2D.Double(xC,yC);
			vertex = findClosestVertex( xC, yC );
			drag = true;
			repaint();
		}
		/**
		 * An abstract method of the interface 
		 * @param e 
		 */
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		/**
		 * An abstract method of the interface 
		 * @param e 
		 */
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		/**
		 * An abstract method of the interface 
		 * @param e 
		 */
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		/**
		 * This method moves the cube to the released position of the mouse
		 * @param e Event that occurs when mouse is released 
		 */
		public void mouseReleased(MouseEvent e) {
			drag = false;
			double xC = e.getX();
			double yC = e.getY();
			mousePointer = new Point2D.Double(xC,yC);
			double xDifference = xC - vertex.x;
			double yDifference = yC - vertex.y;
			moveTo(x+xDifference, y+yDifference);
		}
		
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * This method keeps track of the points about where the mouse is present at a moment 
		 * @param event This is the event that occurs when mouse is dragged
		 */
		public void mouseDragged(MouseEvent event) {
			while ( drag ){
				double x = event.getX();
				double y = event.getY();
				mousePointer = new Point2D.Double(x,y);
			}
		}
	}//mouse class ends
}
