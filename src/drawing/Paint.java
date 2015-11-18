package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint {

	private JFrame frame;
	private JLabel status;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	
	private JPanel buttonPanel;
	private JPanel statusPanel;
	private JPanel bottomPanel;
	private JPanel mainPanel;
	
	private Drawing drawing;
	
	private JLabel info;
	private JLabel text;
	private JLabel infoShape;
	private JLabel nbShape;
	private Observer obs;
	
	public void run(){
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout());
		bottomPanel = new JPanel(new BorderLayout());
		
		obs = new Observer(this);
		
		drawing = new Drawing(obs);
		drawing.setBackground(Color.WHITE);
		
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		info = new JLabel("nombre de formes :");
		text = new JLabel("0");
		infoShape = new JLabel("Select"); 
		nbShape = new JLabel("0");
		status = new JLabel("Status Bar");
		status.setFont(new Font("Sans Serif", Font.ITALIC, 12));
		statusPanel = new JPanel();
		statusPanel.setBorder(new EtchedBorder (EtchedBorder.LOWERED));
		statusPanel.add(status);
		statusPanel.add(info);	
		statusPanel.add(text);
		statusPanel.add(infoShape);	
		statusPanel.add(nbShape);	
		
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		
		bottomPanel.add(buttonPanel, BorderLayout.NORTH);
		bottomPanel.add(statusPanel, BorderLayout.SOUTH);	
		
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);
		
		//listeners pour les boutons
		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));
		
		//listeners pour la zone de dessin
		DrawingMouseListener l = new DrawingMouseListener(drawing);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(640,480);
		frame.setVisible(true);
		
		
	}
	public void setTextSelect (String nb){
		nbShape.setText(nb);
	}
	
	public void setText(String nb){
		text.setText(nb);
	}
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
	}
}
