package drawing;

public class Observer {
	private Paint paint;

	public Observer(Paint p){
		paint = p;

	}
	
	public void updateText(String nb){
		paint.setText(nb);
	}
	
	public void updateSelectText(String nb){
		paint.setTextSelect(nb);
	}
}

