package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Listener pour g�rer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {

	Drawing drawing;
	Shape currentShape = null;
	ArrayList<Shape> shapeList = new ArrayList<Shape>();
	int nbshape = 0;
	public DrawingMouseListener(Drawing d){
		drawing = d;
	}
	
	/**
	 * Bouge la forme s�lectionn�e (si une forme est s�lectionn�e)
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentShape != null){
			currentShape.setOrigin(e.getPoint());
			drawing.repaint();
		}
		if(shapeList.size() != 0){
			for(Shape s: shapeList){
				//s.origin.x = s.origin.x + e.getX();
				//s.origin.y = s.origin.y + e.getPoint().x;
				
				s.setOrigin(e.getPoint());
				drawing.repaint();
			}
		}
	}
	
	/**
	 * S�lectionne la forme sur laquelle l'utilisateur a cliqu�
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape s : drawing){
			if(s.isOn(e.getPoint())){
				currentShape = s;
				break;
			}
		}
	}

	/**
	 * D�s�lectionne la forme lorsqu'on lache le clic gauche
	 */
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			currentShape = null;
			if (shapeList.size() != 0) {
				shapeList.clear();
				nbshape = 0;
				drawing.updateNbSelect(Integer.toString(0));
				//drawing.updateStatus("Objects unselected");
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Selectionne des formes par un clic droit
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 3){
			for(Shape s : drawing){
				if(s.isOn(e.getPoint())){
					shapeList.add(s);
					nbshape ++;
					drawing.updateNbSelect(Integer.toString(nbshape));
					//drawing.updateStatus("Selected objects : " + shapeList.size());
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
