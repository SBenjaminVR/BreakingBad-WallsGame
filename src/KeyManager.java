
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hglez
 */
public class KeyManager implements KeyListener {
    
    public boolean left;  // flag to move left the player
    public boolean right; // flag to move right the player
    public boolean enter; // flag to restart game
    
    private boolean keys[]; // to store the flags for every key
    private boolean start;
    private boolean pause; //flag to pause the game
    private boolean load; 
    private boolean save; 
    private boolean lastPause; 
    private boolean lastSave;
    private boolean lastLoad;

    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // set true every key pressed
         keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            start = true;
        }
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
    
    public boolean getPause() {
        return pause;
    }
    
    public boolean getSave() {
        return save;
    }
    
    public boolean getLoad() {
        return load;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        if (lastPause && !keys[KeyEvent.VK_P]) {
            pause = true;
        }
        else {
            pause = false; 
        }
        if (lastSave && !keys[KeyEvent.VK_G]) {
            save = true;
        }
        else {
            save = false; 
        }
        if (lastLoad && !keys[KeyEvent.VK_C]) {
            load = true;
        }
        else {
            load = false; 
        }
        
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        enter = keys[KeyEvent.VK_ENTER];
        lastPause = keys[KeyEvent.VK_P];
        lastLoad = keys[KeyEvent.VK_C];
        lastSave = keys[KeyEvent.VK_G];
    }
}
