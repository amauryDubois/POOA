package drawing;

import javax.swing.*;

import java.awt.*;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 1L;
	ArrayList<Shape> shapeSelect = new ArrayList<Shape>();
	ArrayList<Shape> shapes;
	Observer obs;
	
	public Drawing(Observer o){
		super();
		shapes = new ArrayList<Shape>();
		obs = o;
	}
	
	/**
	 * Impl�mentation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}
	
	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s){
		shapes.add(s);
		obs.updateText(Integer.toString(shapes.size()));
		this.repaint();
	}
	
	/** 
	 * Red�finition de la m�thode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : shapes){
			s.paint(g);
		}
	}
	
	/**
	 * Enl�ve toutes les formes et redessine
	 */
	public void clear(){
		shapes.clear();
		obs.updateText(Integer.toString(shapes.size()));
		this.repaint();
	}
	/**
	 * 
	 * @param nb
	 */
	public void updateNbSelect(String nb){
		obs.updateSelectText(nb);
	}
	/**
	 * duplique les formes
	 */
	public void duplicate(){
		for( Shape s : shapeSelect){
			Point p = new Point(s.origin.x, s.origin.y+100);
			if(s instanceof Rectangle){
				
				Rectangle r = new Rectangle(p, ((Rectangle) s).width, ((Rectangle) s).height, ((Rectangle) s).color);
				shapes.add(r);
				
			}
			else if (s instanceof Circle){
				Circle c = new Circle(p, ((Circle) s).getRadius(),((Circle) s).getColor());
				shapes.add(c);
			}
			this.repaint();
		}
	}
}
