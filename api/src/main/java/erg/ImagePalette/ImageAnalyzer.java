package erg.ImagePalette;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ImageAnalyzer {

    private static final Logger LOG = Logger.getLogger(ImageAnalyzer.class.getName());

    public static ImageData getColorFrequency(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File("src/main/resources/" + path));
        LOG.info(img.toString());
        FastRGB frgb = new FastRGB(img);
        return new ImageData(frgb.getRGBArray());
    }
}
