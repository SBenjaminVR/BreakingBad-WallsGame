
import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.cos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hglez
 */

public class Projectile extends Item {
    
    private int width;
    private int height;
    private Game game;
    private Rectangle hitbox;
    private Animation anim; // to store the animation for being destroyed
    public enum ballStatus{
        idle, base, fallen
    }
    private ballStatus state;
    private int dirX;
    private int dirY;
    private double xSpeed;
    private double ySpeed;
    private double speed;
    
    public Projectile(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.state = ballStatus.idle;
        this.dirX = -1;
        this.dirY = -1;
        this.speed = 8;
        this.xSpeed = 0;
        this.ySpeed = -8;
        this.hitbox = new Rectangle(x, y, width, height);  
        this.anim = new Animation(Assets.grenade, 100);
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

    public void setState(ballStatus state) {
        this.state = state;
    }

    public ballStatus getState() {
        return state;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public double getXSpeed() {
        return xSpeed;
    }
    
    public double getYSpeed() {
        return ySpeed;
    }

    public void setXSpeed(double speed) {
        this.xSpeed = speed;
    }
    
    public void setYSpeed(double speed) {
        this.ySpeed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    @Override
    public void tick() {
        hitbox.setLocation(getX(), getY());
        //this.animationRight.tick();
        if (getState() == ballStatus.idle) {
            if (game.getKeyManager().isStart()) {
                setState(ballStatus.base);
            }
        }
        else if (getState() == ballStatus.base) { 
            anim.tick();
            // player loses if ball falls
            if (getY() + getHeight() >= game.getHeight()) {
              setState(ballStatus.fallen);
            }
            // Collision with walls
            if (getX() + getWidth() >= game.getWidth() || getX() <= 0) {
                setXSpeed(getXSpeed() * -1);
            }
            else if (getY() <= 0) {
                setYSpeed(getYSpeed() * -1);
            }

            // Collision with player
            if (hitbox.intersects(game.getPlayer().getHitbox())) {
                int position = (getX() + getWidth() / 2) - (int)game.getPlayer().getHitbox().getX();
                double angle;
                boolean right = false;
                if (position < 0) {
                    position = 0;
                }
                else if (position > game.getPlayer().getHitbox().getWidth()) {
                    position = (int)game.getPlayer().getHitbox().getWidth();
                }
                
                if (position <= game.getPlayer().getHitbox().getWidth()/2) {
                    angle = (position / (game.getPlayer().getHitbox().getWidth() / 2.0) ) * 40 + 30;
                    right = false;
                }
                else {
                    position -= game.getPlayer().getHitbox().getWidth() / 2;
                    angle = ((game.getPlayer().getHitbox().getWidth() / 2 - position) / (game.getPlayer().getHitbox().getWidth() / 2)) * 40 + 30;
                    right = true;
                }
                angle = Math.toRadians(angle);
                setXSpeed((right ? 1 : -1) * getSpeed() * Math.cos(angle));
                setYSpeed(-1 * getSpeed() * Math.sin(angle));
            }
                      
            setX((int) (getX() + getXSpeed()));
            setY((int) (getY() + getYSpeed()));
        }
    }
    
    @Override
    public void render(Graphics g) {
       g.drawImage(anim.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
