
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hglez
 */
public class Game implements Runnable {
    
    private BufferStrategy bs; //to have several buffers when displaying
    private Graphics g; //to paint objects
    private Display display; //to display in the game
    String title; //title of the window
    private int width; //width of the window
    private int height; //height of the window
    private Thread thread; //thread to create the game
    private boolean running; //to set the game
    private Player player; // to use a player
    private LinkedList<Brick> bricks ;// to use the bricks 
    private Projectile ball;
    private KeyManager keyManager; // to manage the keyboard
    private WriteFile saveFile;
    private enum gameState { normal, gameOver, pause }
    
    private gameState gameState;
    private int timerBrick;
    private int score;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        this.gameState = gameState.normal;
        bricks = new LinkedList<Brick>();
        timerBrick = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Projectile getBall() {
        return ball;
    }

    public gameState getGameState() {
        return gameState;
    }

    public void setGameState(gameState gameState) {
        this.gameState = gameState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
        
    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player(getWidth()/2 - 113, getHeight() - 75, 1, 226, 50, this);
        ball = new Projectile(player.getX() + player.getWidth()/2 - 25, player.getY() - 51, 50, 50, this);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                int iPosX = j*141; 
                int iPosY = 158 - i*47;
                bricks.add(new Brick(iPosX, iPosY, 155, 55, this));
            }
        }

        display.getJframe().addKeyListener(keyManager);

        try {
            saveFile = new WriteFile("test.txt", false);
            saveFile.writeToFile("Benja es cool");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 60;
        // time for each tick in nanoseconds
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta  = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    
    private void tick() {
        timerBrick--;
        keyManager.tick();
        if (keyManager.getPause() && getGameState() == gameState.normal) {
                setGameState(gameState.pause);
            
        }
        else {
            if (keyManager.getPause() && getGameState() == gameState.pause) {
                setGameState(gameState.normal);
            }
        }
        
        if (getGameState() == gameState.normal) {
            if (getPlayer().getState() == Player.playerState.dead) {
                setGameState(gameState.gameOver);                
            }
            // advancing player with collision
            player.tick();
            ball.tick();
            for (int i = 0; i < bricks.size(); i++) {
                Brick myBrick = bricks.get(i);
                myBrick.tick();
            }
            
            for(int j = 0; j < bricks.size(); j++){
                Brick myBrick = bricks.get(j);

                boolean yIntersects = ball.getHitbox().intersects(myBrick.getyHitbox());
                boolean xIntersects = ball.getHitbox().intersects(myBrick.getxHitbox());

                if(yIntersects && timerBrick <= 0){
//                        player.setScore(player.getScore() + 5);
                    if (myBrick.getState() == Brick.status.normal) myBrick.setState(Brick.status.hit);
                    else if (myBrick.getState() == Brick.status.hit) myBrick.setState(Brick.status.destroyed);
                    yIntersects = false;

                    timerBrick = 10;

                    ball.setYSpeed(ball.getYSpeed() * -1);
                }
                else if (xIntersects && timerBrick <= 0){
//                        player.setScore(player.getScore() + 5);
                    if (myBrick.getState() == Brick.status.normal) myBrick.setState(Brick.status.hit);
                    else if (myBrick.getState() == Brick.status.hit) myBrick.setState(Brick.status.destroyed);
                    xIntersects = false;

                    timerBrick = 10;

                    ball.setXSpeed(ball.getXSpeed() * -1);
                }
                
                if (myBrick.getState() == Brick.status.destroyed) {
                    bricks.remove(j);
                }
            }
        }

        if (getGameState() == gameState.gameOver) {
             for (int i = 0; i < bricks.size(); i++) {
                 bricks.remove(i);
             }
            if (keyManager.enter) {
                setGameState(gameState.normal);
                keyManager.setStart(false);
                player = new Player(getWidth()/2 - 113, getHeight() - 75, 1, 226, 50, this);
                ball = new Projectile(player.getX() + player.getWidth()/2 - 25, player.getY() - 51, 50, 50, this);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 9; j++) {
                        int iPosX = j * 141;
                        int iPosY = 158 - i * 47;
                        bricks.add(new Brick(iPosX, iPosY, 155, 55, this));
                    }
                }
            }
        }
    }
    
    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /*if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectangle, getting the graphic object from the
        buffer strategy element.
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else {
            g = bs.getDrawGraphics();
            if (getGameState() == gameState.normal || getGameState() == gameState.pause) {
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                ball.render(g);
                for (int i = 0; i < bricks.size(); i++) {
                    Brick myBrick = bricks.get(i);
                    myBrick.render(g);
                }
                if (getGameState() == gameState.pause) {
                    g.drawImage(Assets.pause, 0, 0, getWidth(), getHeight(), null);
                }
            }
            
            if (getGameState() == gameState.gameOver) {
                g.drawImage(Assets.gameOver, 0, 0, getWidth(), getHeight(), null);
            }
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }    
}
