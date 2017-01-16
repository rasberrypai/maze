import java.awt.Color;
import java.awt.Graphics;

public class Connector extends Rooms{
	public boolean yeah = false;
	public Connector(){
		
	}
	public void visit(){
		yeah = true;
	}
	public boolean visited(){
		return yeah;
	}
}
