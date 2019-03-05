
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Hglez
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player; // to store the player image
    public static BufferedImage sprites; // to store the sprites
    public static BufferedImage playerBar[]; // pictures to go up
    public static BufferedImage destroyEffect[]; // picture for being destroyed
    public static BufferedImage drug;
    public static BufferedImage temp;
    public static BufferedImage gameOver; // to store game over image
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/slime.png");
        temp = ImageLoader.loadImage("/images/ball.png");
        gameOver = ImageLoader.loadImage("/images/gameoverscreen.jpg");
        
        sprites = ImageLoader.loadImage("/images/BarritaSprite.png");
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        
        playerBar = new BufferedImage[18];
        
        destroyEffect = new BufferedImage[4];
        // cropping the pictures from the sheet into the array
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                playerBar[count] = spritesheet.crop(i * 226, j * 50, 226, 50);
                count++;
            }
        }
    }
}
