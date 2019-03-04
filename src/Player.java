
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hglez
 */

public class Player extends Item {
    // 32 x 20
    private int direction;
    private int width;
    private int height;
    private Game game;
    private int speed;
    private boolean Chocado;
    private Animation animationUp; // to store the animation for going up
    private Animation animationLeft; // to store the animation for going left
    private Animation animationRight; // to store the animation for going right
    private Animation animationDown; // to store the animation for going down
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.Chocado = false;
        
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
    }
    
     public int getDirection() {
        return direction;
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

    public boolean isChocado() {
        return Chocado;
    }

    public void setChocado(boolean Chocado) {
        this.Chocado = Chocado;
    }
    
    @Override
    public void tick() {
        this.animationRight.tick();
        
        // moving player depending on flags
        if (game.getKeyManager().up) {
            setY(getY() - getSpeed());
        }
        if (game.getKeyManager().down) {
            setY(getY() + getSpeed());
        }
        if (game.getKeyManager().left) {
            setX(getX() - getSpeed());
        }
        if (game.getKeyManager().right) {
            setX(getX() + getSpeed());
        }
        
        // reset x position and y position if collision
        if (getX() + 60 >= game.getWidth()) {
            setChocado(true);
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setChocado(true);
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setChocado(true);
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setChocado(true);
            setY(-20);
        }
    }
    
    @Override
    public void render(Graphics g) {
       g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
