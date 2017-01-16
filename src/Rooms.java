import java.awt.Color;
import java.awt.Graphics;

public class Rooms {
	public Rooms(){
		
	}
	public void generate(Graphics g, int x, int y){
		g.setColor(Color.white);
		g.fillRect(x, y, 5, 5);
	}
}
