package erg.ImagePalette;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageData {

    private static final Logger LOG = Logger.getLogger(ImageData.class.getName());

    @JsonProperty int numPixels;
    @JsonProperty int numDistinctColors;
    @JsonProperty int[] colorSet;
    @JsonProperty int numColorPixels[];

    //Assume inputs are same length
    public ImageData(int[] pixels) {
        this.numPixels = pixels.length;
        HashMap<Integer, Integer> colorCount = new HashMap<>();
        for(int i = 0; i < numPixels; i++) {
            if(!colorCount.containsKey(pixels[i])) {
                colorCount.put(pixels[i], 1);
            } else {
                colorCount.put(pixels[i], colorCount.get(pixels[i]) + 1);
            }
        }
        int l = colorCount.keySet().size();
        this.colorSet = new int[l];
        this.numColorPixels = new int[l];
        this.numDistinctColors = l;
        Integer[] keys = new Integer[l];
        Iterator<Integer> it = colorCount.keySet().iterator();
        int k = 0;
        while(it.hasNext()) {
            keys[k] = it.next();
            k++;
        }
        colorCount.keySet();
        for(int i = 0; i < l; i++) {
            colorSet[i] = keys[i];
            numColorPixels[i] = colorCount.get(keys[i]);
        }
    }

    public static ImageData fromFile(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File("src/main/resources/" + path));
        LOG.info(img.toString());
        FastRGB frgb = new FastRGB(img);
        return new ImageData(frgb.getRGBArray());
    }

}
