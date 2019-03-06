
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hglez
 */

public class Brick extends Item {
    
    private int width;
    private int height;
    private Game game;
    private Rectangle xHitbox;
    private Rectangle yHitbox;
    private Animation destroyEffect; // to store the animation for being destroyed
    public enum status { normal, hit, destroyed }
    private status state;
    private int lives;
    private int bTimer;
    private boolean animOver;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.xHitbox = new Rectangle(x, y+15, width-13, height-20);
        this.yHitbox = new Rectangle(x + 10, y+8, width-28, height-8);
        this.destroyEffect = new Animation(Assets.destroyEffect, 100);
        this.state = status.normal;
        this.lives = 2;
        this.bTimer = 0;
        this.animOver = false;
    }
    
     
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
        
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public status getState() {
        return state;
    }

    public void setState(status state) {
        this.state = state;
    } 

    public Rectangle getxHitbox() {
        return xHitbox;
    }

    public Rectangle getyHitbox() {
        return yHitbox;
    }

    public boolean isAnimOver() {
        return animOver;
    }
    
    
    
    @Override
    public void tick() {
        if (getState() == status.destroyed) {
            destroyEffect.tick();
            if (destroyEffect.getIndex() == 5) {
                animOver = true;
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
       if (getState() == status.normal) {
            g.drawImage(Assets.drug, getX(), getY(), getWidth(), getHeight(), null);
       }
       if (getState() == status.hit) {
            g.drawImage(Assets.damagedBrick, getX(), getY(), getWidth(), getHeight(), null);
       }
       if (getState() == status.destroyed) {
           g.drawImage(destroyEffect.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
       }
       
    }
}
