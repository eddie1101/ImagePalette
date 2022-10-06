package erg.ImagePalette;

import java.util.Arrays;

public class ImageData {

    int numPixels;
    int[] r, g, b, a;
    int[] colorSet;
    int[] numColorPixels;

    //Assume inputs are same length
    public ImageData(int[] pixels) {
        this.r = new int[pixels.length];
        this.g = new int[pixels.length];
        this.b = new int[pixels.length];
        this.a = new int[pixels.length];
        for(int i = 0; i < pixels.length; i++) {
            r[i] = pixels[i] >> 24 & 0xFF;
            g[i] = pixels[i] >> 16 & 0xFF;
            b[i] = pixels[i] >> 8 & 0xFF;
            a[i] = pixels[i] & 0xFF;

            

        }
    }
}
