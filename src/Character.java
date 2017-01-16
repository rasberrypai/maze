import java.awt.Color;
import java.awt.Graphics;

public class Character extends Rooms{
	private int x = 0;
	private int y = 0;
	public Character(){
		
	}
	public void draw(Graphics g, int x, int y){
		g.setColor(Color.RED);
		g.fillRect(x, y, 5, 5);
	}
}
