
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
    public static BufferedImage barSprites; // to store the sprites
    public static BufferedImage playerBar[]; // pictures to go up
    public static BufferedImage destroyEffect[]; // picture for being destroyed
    public static BufferedImage drug;
    public static BufferedImage damagedBrick;  
    public static BufferedImage grenadeSprites;
    public static BufferedImage grenade[];
    public static BufferedImage gameOver; // to store game over image
    public static BufferedImage pause; //to store the pause image
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        gameOver = ImageLoader.loadImage("/images/gameoverscreen.jpg");
        drug = ImageLoader.loadImage("/images/methSprite.png"); 
        damagedBrick = ImageLoader.loadImage("/images/damagedSprite.png"); 
        pause = ImageLoader.loadImage("/images/Pausa.png"); 
        
        barSprites = ImageLoader.loadImage("/images/BarritaSprite.png");
        SpreadSheet barSpritesheet = new SpreadSheet(barSprites);
         
        playerBar = new BufferedImage[18];
        grenadeSprites = ImageLoader.loadImage("/images/granadaSprites.png");
        SpreadSheet grenadeSpritesheet = new SpreadSheet(grenadeSprites);
        grenade = new BufferedImage[35];
        destroyEffect = new BufferedImage[4];
        // cropping the pictures from the sheet into the array
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                playerBar[count] = barSpritesheet.crop(i * 226, j * 50, 226, 50);
                count++;
            }
        }
        
        count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (count > 34) break;
                grenade[count] = grenadeSpritesheet.crop(i * 50, j * 50, 50, 50);
                count++;
            }
        }
    }
}
