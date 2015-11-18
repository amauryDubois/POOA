package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 1L;
	
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
	public void updateNbSelect(String nb){
		obs.updateSelectText(nb);
	}
	
}
