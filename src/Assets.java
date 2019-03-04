
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
    public static BufferedImage playerUp[]; // pictures to go up
    public static BufferedImage playerDown[]; // pictures to go down
    public static BufferedImage playerLeft[]; // pictures to go left
    public static BufferedImage playerRight[]; // pictures to go right
    
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/slime.png");
        
        sprites = ImageLoader.loadImage("/images/mariosprites.png");
        SpreadSheet spritesheet = new SpreadSheet(sprites);
        
        playerUp = new BufferedImage[8];
        playerLeft = new BufferedImage[8];
        playerDown = new BufferedImage[8];
        playerRight = new BufferedImage[8];
        // cropping the pictures from the sheet into the array
        for (int i = 0; i < 8; i++) {
            playerUp[i] = spritesheet.crop(i * 20, 0, 20, 32);
            playerLeft[i] = spritesheet.crop(i * 20, 32, 20, 32);
            playerRight[i] = spritesheet.crop(i * 20, 64, 20, 32);
            playerDown[i] = spritesheet.crop(i * 20, 96, 20, 32);            
        }
    }
}
