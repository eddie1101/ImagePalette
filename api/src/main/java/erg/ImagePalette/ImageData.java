package erg.ImagePalette;

import java.util.logging.Logger;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageData {

    private static final Logger LOG = Logger.getLogger(ImageData.class.getName());

    @JsonProperty int numPixels;
    @JsonProperty ColorProfile colorProfile;

    //Assume inputs are same length
    private ImageData(int[] pixels, ImageColorProfiler colorProfiler) {
        this.numPixels = pixels.length;
        this.colorProfile = colorProfiler.getColorProfile(pixels, 0);
    }

    public static ImageData fromFile(String path, ImageColorProfiler colorProfiler) throws IOException {
        BufferedImage img = ImageIO.read(new File("src/main/resources/" + path));
        LOG.info(img.toString());
        FastRGB frgb = new FastRGB(img);
        return new ImageData(frgb.getRGBArray(), colorProfiler);
    }

}
