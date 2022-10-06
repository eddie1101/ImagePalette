package erg.ImagePalette;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageAnalyzer {

    public ImageData getColorFrequency(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/" + path));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        if(img == null) return null;

        return new ImageData(img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, 0));

    }

}
