
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
    private enum status { normal, hit, destroyed }
    private status state;
    private int lives;
    private int bTimer;
    
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
        bTimer--;
        if (bTimer <= 0) game.getBall().setCollision(false);
        if (!game.getBall().isCollision()) {
            if (yHitbox.intersects(game.getBall().getHitbox())) {
                
                game.getBall().setYSpeed(game.getBall().getYSpeed() * -1);
                if (getState() == status.normal) {
                    setState(status.hit);
                }
                else {
                    setState(status.destroyed);
                }
                game.getBall().setCollision(true);                
            }
            else if (xHitbox.intersects(game.getBall().getHitbox())) {
                
                game.getBall().setXSpeed(game.getBall().getXSpeed() * -1);
                if (getState() == status.normal) {
                    setState(status.hit);
                }
                else {
                    setState(status.destroyed);
                }
                game.getBall().setCollision(true);
            }
            bTimer = 24;
        }
        
        if (getState() == status.destroyed) {
            this.destroyEffect.tick();            
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
       if (getState() != status.destroyed) {
        g.setColor(Color.GREEN);
        g.drawRect(xHitbox.x, xHitbox.y, xHitbox.width, xHitbox.height);
        g.setColor(Color.RED);
        g.drawRect(yHitbox.x, yHitbox.y, yHitbox.width, yHitbox.height);
       }
    }
}
