
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
    private Animation destroyEffect; // to store the animation for being destroyed
    public enum status { normal, hit, destroyed }
    private status state;
    private int lives;
    private int bTimer;
    private boolean animOver;
    private boolean destroySndDone;
    private SoundClip destroySound;
    private SoundClip hitSound;
    private boolean hitSndDone;
    private int dropProb; // variable to check if brick can drop powerup
    private int goodDropChance; // Chance to drop good powerup
    private int badDropChance; // Chance to drop bad powerup
    private boolean alreadyDropped;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.destroyEffect = new Animation(Assets.destroyEffect, 100);
        this.state = status.normal;
        this.lives = 2;
        this.animOver = false;
        this.destroySound = Assets.destroySound;
        this.destroySndDone = false;
        this.hitSound = Assets.hitSound;
        this.hitSndDone = false;
        this.dropProb = (int) (Math.random() * 100); // random number from 0 to 100
        this.goodDropChance = 10; // if dropProb is <= goodDropChance drop it
        this.badDropChance = 90; // if dropProb is >= badDropChance drop it
        this.alreadyDropped = false;
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

    public boolean isAnimOver() {
        return animOver;
    }

    public int getbTimer() {
        return bTimer;
    }

    public void setbTimer(int bTimer) {
        this.bTimer = bTimer;
    }

    public int getDropProb() {
        return dropProb;
    }

    public int getBadDropChance() {
        return badDropChance;
    }

    public int getGoodDropChance() {
        return goodDropChance;
    }

    public boolean isAlreadyDropped() {
        return alreadyDropped;
    }

    public void setAlreadyDropped(boolean alreadyDropped) {
        this.alreadyDropped = alreadyDropped;
    }    
        
    @Override
    public void tick() {
        if (getState() == status.hit) {
            if (!hitSndDone) {
                hitSound.play();
                hitSndDone = true;
            }
        }
        if (getState() == status.destroyed) {
            if (!destroySndDone) {
                destroySound.play();
                destroySndDone = true;
            }            
            destroyEffect.tick();
            if (destroyEffect.getIndex() == 5) {
                animOver = true;
            }
        }
    }
    
    public Rectangle getPerimetro() {

            return new Rectangle(getX(), getY(), getWidth(), getHeight());
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
