
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
    private Rectangle hitbox;
    private Animation destroyEffect; // to store the animation for being destroyed
    private enum status { normal, hit }
    private status state;
    private int lives;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.hitbox = new Rectangle(x, y+8, width-13, height-8);
        this.destroyEffect = new Animation(Assets.destroyEffect, 100);
        this.state = status.normal;
        this.lives = 2;
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
    
    
    @Override
    public void tick() {
        if (game.getBall().getHitbox().intersects(hitbox)) {
            setState(status.hit);
            
        }
        /*
        if (getState() == status.hit) {
            this.destroyEffect.tick();
            
        }
*/
        
    }
    
    @Override
    public void render(Graphics g) {
       if (getState() == status.normal) {
        g.drawImage(Assets.drug, getX(), getY(), getWidth(), getHeight(), null);
       }
       if (getState() == status.hit) {
        g.drawImage(Assets.damagedBrick, getX(), getY(), getWidth(), getHeight(), null);
       }
    }
}
