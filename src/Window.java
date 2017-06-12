import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dimensions = 505;
	private int size = dimensions/5;
	private final int DELAY = 15; // make this higher if you want it to be slower
	private Timer timer;
	private int x = 0;
	private int y = 0;
	public Rooms[][][] rooms = new Rooms[size][size][2];
	public Window(){
        setBackground(Color.black);
        setPreferredSize(new Dimension(dimensions, dimensions));
        timer = new Timer(DELAY, this);
			timer.start();
		buildMaze();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawRooms(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	private int complexity(){
		int complexity = 0;
		
		return complexity;
	}
	private void buildMaze(){
		if( x == 0 && y == 0){
			start(x, y);
			rooms[size-1][size-1][0] = new EndPoint();
		}
		while(!deadend()){
			generateMaze();
			if(deadend()){
				System.out.println("neil");
				retrace();
			}
		}
		connectEnd();
	}
	private void connectEnd(){
		if(rooms[size-3][size-1][0] instanceof Rooms && rooms[size-1][size-3][0] instanceof Rooms){
			int temp = (int) (Math.random()*2);
			if(temp == 0){
				rooms[size-2][size-1][0] = new Connector();
			} else if(temp == 1){
				rooms[size-1][size-2][0] = new Connector();
			}
		} else if(rooms[size-3][size-1][0] instanceof Rooms){
			rooms[size-2][size-1][0] = new Connector();
		} else if(rooms[size-1][size-3][0] instanceof Rooms){
			rooms[size-1][size-2][0] = new Connector();
		}
	}
	private void generateMaze(){
		int direction = (int) (Math.random()*4);
		System.out.println(direction);
		if(direction == 0){ //right
			if(!noRight()){
				rooms[x+1][y][0] = new Connector();
				rooms[x+2][y][0] = new Rooms();
				x+=2;
			}
		} else if(direction == 1){ //left
			if(!noLeft()){
				rooms[x-1][y][0] = new Connector();
				rooms[x-2][y][0] = new Rooms();
				x-=2;
			}
		} else if(direction == 2){ //up
			if(!noUp()){
				rooms[x][y-1][0] = new Connector();
				rooms[x][y-2][0] = new Rooms();
				y-=2;
			}
		} else if(direction == 3){ //down
			if(!noDown()){
				rooms[x][y+1][0] = new Connector();
				rooms[x][y+2][0] = new Rooms();
				y+=2;
			}
		}
	}
	private void retrace(){
		while(deadend()){
			boolean go = true;
			if(x+1 < rooms.length && rooms[x+1][y][0] instanceof Connector){
				if((((Connector) rooms[x+1][y][0]).visited())){
					
				} else {
					go = false;
					((Connector) rooms[x+1][y][0]).visit();
					x+=2;
				}
			} 
			if(go){
				if(x-1 >= 0 && rooms[x-1][y][0] instanceof Connector){
					if((((Connector) rooms[x-1][y][0]).visited())){
						
					} else {
						go = false;
						((Connector) rooms[x-1][y][0]).visit();
						x-=2;
					}
				} 
			}
			if(go){
				if(y+1 < rooms.length && rooms[x][y+1][0] instanceof Connector){
					if((((Connector) rooms[x][y+1][0]).visited())){
						
					} else { 
						go = false;
						((Connector) rooms[x][y+1][0]).visit();
						y+=2;
					}
				} 
			}
			if(go){
				if(y-1 >= 0 && rooms[x][y-1][0] instanceof Connector){
					if((((Connector) rooms[x][y-1][0]).visited())){
						
					} else { 
						go = false;
						((Connector) rooms[x][y-1][0]).visit();
						y-=2;
					}
				}
			}
			if(noConnectors() && deadend()){
				System.out.println("breaks");
				break;
			}
		}
	}
	private boolean noConnectors(){
		boolean no = true;
		if(x+1 < rooms.length){
			if(rooms[x+1][y][0] instanceof Connector){
				if((((Connector) rooms[x+1][y][0]).visited())){
					
				} else {
					no = false;
				}
			} 
		}
		if(x-1 >= 0){
			if(rooms[x-1][y][0] instanceof Connector){
				if((((Connector) rooms[x-1][y][0]).visited())){
					
				} else {
					no = false;
				}
			}
		}
		if(y+1 < rooms.length){
			if(rooms[x][y+1][0] instanceof Connector){
				if((((Connector) rooms[x][y+1][0]).visited())){
					
				} else {
					no = false;
				}
			}
		}
		if(y-1 >= 0){
			if(rooms[x][y-1][0] instanceof Connector){
				if((((Connector) rooms[x][y-1][0]).visited())){
					
				} else {
					no = false;
				}
			} 
		}
		return no;
	}
	private void start(int x, int y){
		rooms[x][y][0] = new Rooms();
		rooms[x][y][1] = new Character();
	}
	private boolean deadend(){
		boolean yeah = false;
		if(noRight() && noLeft() && noUp() && noDown()){
			yeah = true;
			System.out.println("deadend");
		}
		
		return yeah;
	}
	private boolean noRight(){
		boolean yeah = false;
		if(x+2 >= rooms.length){
			yeah = true;
		} else if(rooms[x+2][y][0] instanceof Rooms){
			yeah = true;
		}
		return yeah;
	}
	private boolean noLeft(){
		boolean yeah = false;
		if(x-2 < 0){
			yeah = true;
		} else if(rooms[x-2][y][0] instanceof Rooms){
			yeah = true;
		}
		return yeah;
	}
	private boolean noUp(){
		boolean yeah = false;
		if(y-2 < 0){
			yeah = true;
		} else if(rooms[x][y-2][0] instanceof Rooms){
			yeah = true;
		}
		return yeah;
	}
	private boolean noDown(){
		boolean yeah = false;
		if(y+2 >= rooms.length){
			yeah = true;
		} else if(rooms[x][y+2][0] instanceof Rooms){
			yeah = true;
		}
		
		return yeah;
	}
	private void drawRooms(Graphics g){
		for(int i = 0; i < rooms.length; i++){
			for(int ii = 0; ii < rooms.length; ii++){
				int x = ii*5;
				int y = i*5;
				if(rooms[ii][i][0] instanceof Rooms || rooms[ii][i][0] instanceof Connector){
					rooms[ii][i][0].generate(g, x, y);
				}
				if(rooms[ii][i][1] instanceof Character){
					((Character) rooms[ii][i][1]).draw(g, x, y);
				}
				if(rooms[ii][i][0] instanceof EndPoint){
					((EndPoint) rooms[ii][i][0]).draw(g, x, y);
				}
			}
		}
	}
}
