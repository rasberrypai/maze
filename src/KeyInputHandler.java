import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class KeyInputHandler implements KeyListener {
	private int characterX;
	private int characterY;
	private int k;
	public void keyPressed(KeyEvent e) {
        k = e.getKeyCode();
        if(k == KeyEvent.VK_UP){
        	findCharacter();
        	if(!noUp()){
	        	Run.win.rooms[characterX][characterY][1] = null;
	        	Run.win.rooms[characterX][characterY][0] = null;
	        	Run.win.rooms[characterX][characterY-1][1] = new Character();
        	}
        }
        if(k == KeyEvent.VK_DOWN){
        	findCharacter();
        	if(!noDown()){
        		Run.win.rooms[characterX][characterY][1] = null;
	        	Run.win.rooms[characterX][characterY][0] = null;
        		Run.win.rooms[characterX][characterY+1][1] = new Character();
        	}
        }
        if(k == KeyEvent.VK_RIGHT){
        	findCharacter();
        	if(!noRight()){
        		Run.win.rooms[characterX][characterY][1] = null;
	        	Run.win.rooms[characterX][characterY][0] = null;
        		Run.win.rooms[characterX+1][characterY][1] = new Character();
        	}
        }
        if(k == KeyEvent.VK_LEFT){
        	findCharacter();
        	if(!noLeft()){	
        		Run.win.rooms[characterX][characterY][1] = null;
	        	Run.win.rooms[characterX][characterY][0] = null;
        		Run.win.rooms[characterX-1][characterY][1] = new Character();
        	}
        }
    }
	private void findCharacter(){
		for(int ii = 0; ii < Run.win.rooms.length; ii++){
			for(int i = 0; i < Run.win.rooms.length; i++){
				if(Run.win.rooms[i][ii][1] instanceof Character){
					this.characterX = i;
					this.characterY = ii;
				}
			}
		}
	}
	private boolean noRight(){
		boolean yeah = false;
		if(characterX+1 >= Run.win.rooms.length){
			yeah = true;
		} else if(!(Run.win.rooms[characterX+1][characterY][0] instanceof Rooms)){
			yeah = true;
		}
		return yeah;
	}
	private boolean noLeft(){
		boolean yeah = false;
		if(characterX-1 < 0){
			yeah = true;
		} else if(!(Run.win.rooms[characterX-1][characterY][0] instanceof Rooms)){
			yeah = true;
		}
		return yeah;
	}
	private boolean noUp(){
		boolean yeah = false;
		if(characterY-1 < 0){
			yeah = true;
		} else if(!(Run.win.rooms[characterX][characterY-1][0] instanceof Rooms)){
			yeah = true;
		}
		return yeah;
	}
	private boolean noDown(){
		boolean yeah = false;
		if(characterY+1 >= Run.win.rooms.length){
			yeah = true;
		} else if(!(Run.win.rooms[characterX][characterY+1][0] instanceof Rooms)){
			yeah = true;
		}
		
		return yeah;
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}