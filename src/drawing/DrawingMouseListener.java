package drawing;

import java.awt.Point;
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
	Point last = new Point();
	int xOrigin= 0;
	int yOrigin= 0;
	int nbshape = 0;
	public DrawingMouseListener(Drawing d){
		drawing = d;
	}
	
	/**
	 * Bouge la forme s�lectionn�e (si une forme est s�lectionn�e)
	 */
	public void mouseDragged(MouseEvent e) {
		int dx = (e.getPoint().x-xOrigin);
		int dy = (e.getPoint().y-yOrigin);
		if(currentShape != null && drawing.shapeSelect.size() == 0){
			currentShape.setOrigin(e.getPoint());
			drawing.repaint();
		}
		if(drawing.shapeSelect.size() != 0){

			for(Shape s: drawing.shapeSelect){
				//Point  curent = new Point ( e.getX(), e.getY());
				
				System.out.println(dx+" "+dy);
				//s.origin.x = s.origin.x + e.getX();
				//s.origin.y = s.origin.y + e.getPoint().x;
				Point pos = new Point(  dx+ s.origin.x, dy+s.origin.y );
				s.setOrigin(pos);
				
				drawing.repaint();
			}
			
		}
		last = e.getPoint();
		xOrigin = e.getX();
		yOrigin = e.getY();
	}
	
	/**
	 * S�lectionne la forme sur laquelle l'utilisateur a cliqu�
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape s : drawing){
			if(s.isOn(e.getPoint())){
				currentShape = s;
				last = e.getPoint();
				xOrigin = e.getX();
				yOrigin = e.getY();
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
			if (drawing.shapeSelect.size() != 0) {
				drawing.shapeSelect.clear();
				nbshape = 0;
				drawing.updateNbSelect(Integer.toString(0));
				last = null;
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
					if(!drawing.IsOn(s)){
						drawing.shapeSelect.add(s);
						nbshape ++;
						drawing.updateNbSelect(Integer.toString(nbshape));
							
					}
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
