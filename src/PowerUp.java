
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benjamin
 */

public class PowerUp extends Item {
    
    private int width;
    private int height;
    private Game game;
    private Rectangle hitbox;
    public enum Type { good, bad }
    private Type type;
    private BufferedImage sprite;
    private int fallSpeed;
    
    public PowerUp(int x, int y, int width, int height, Type type, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.hitbox = new Rectangle(x, y, width, height);        
        this.type = type;
        if (type == Type.good) {
            this.sprite = Assets.goodPwrUp;
        }
        else if (type == Type.bad) {
            this.sprite = Assets.badPwrUp;
        }
        this.fallSpeed = 5;
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

    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFallSpeed() {
        return fallSpeed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }    
    
    @Override
    public void tick() {
        getHitbox().setLocation(getX(), getY());
        setY(getY() + getFallSpeed());
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(sprite, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
