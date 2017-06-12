import java.awt.Color;
import java.awt.Graphics;

public class EndPoint extends Rooms{
	public EndPoint(){
		
	}
	public void draw(Graphics g, int x, int y){
		g.setColor(Color.green);
		g.fillRect(x, y, 5, 5);
	}	
}
