
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Humberto Gonzalez 
 * @author Benjamin Valdez
 */

public class Player extends Item {
    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed;
    private Rectangle hitbox;
    private Animation bar; // to store the animation for going up
    public enum playerState { init, alive, dead }
    private playerState state;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 10;
        this.hitbox = new Rectangle(x, y, width, height/3);
        this.bar = new Animation(Assets.playerBar, 100);
        this.state = playerState.init;
    }
     
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }

    public playerState getState() {
        return state;
    }

    public void setState(playerState state) {
        this.state = state;
    }
    
    
    
    @Override
    public void tick() {
        
        if (state == playerState.init && game.getKeyManager().isStart()) {
            // Start the game
            state = playerState.alive;            
        }
        // Normal game stuff
        if (state == playerState.alive) {
            // refresh animation
            bar.tick();
            // refresh hitbox location
            hitbox.setLocation(getX(), getY());
            
            // Move the bar based on input
            if (game.getKeyManager().left) {
                setX(getX() - getSpeed());
            }
            if (game.getKeyManager().right) {
                setX(getX() + getSpeed());
            }
            
            // Collision with walls
            if (getX() + getWidth() >= game.getWidth()) {
                setX(game.getWidth() - getWidth());
            }
            if (getX() <= 0) {
                setX(0);
            }
            if (game.getBall().getState() == Projectile.ballStatus.fallen) {
                setState(playerState.dead);
            }
        }       
    }
    
    
    
    @Override
    public void render(Graphics g) {
       g.drawImage(bar.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
