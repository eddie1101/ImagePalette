
package erg.ImagePalette;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.util.logging.Logger;

public class FastRGB
{

    private static final Logger LOG = Logger.getLogger(FastRGB.class.getName());

    private int width;
    private int height;
    private boolean hasAlphaChannel;
    private int pixelLength;
    private byte[] pixels;

    FastRGB(BufferedImage image) {
        this.pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.hasAlphaChannel = image.getAlphaRaster() != null;
        this.pixelLength = 3;
        if (hasAlphaChannel)
        {
            this.pixelLength = 4;
        }

    }

    int getRGB(int x, int y) {
        int pos = (y * pixelLength * width) + (x * pixelLength);

        int argb = -16777216; // 255 alpha
        if (hasAlphaChannel)
        {
            argb = (((int) pixels[pos++] & 0xff) << 24); // alpha
        }

        argb += ((int) pixels[pos++] & 0xff); // blue
        argb += (((int) pixels[pos++] & 0xff) << 8); // green
        argb += (((int) pixels[pos++] & 0xff) << 16); // red
        return argb;
    }

    int[] getRGBArray() {
        int size = this.height * this.width;
        int[] pixels = new int[size];
        for(int i = 0; i < size; i++) pixels[i] = getRGB(i % this.width, i / this.width);
        return pixels;
    }

    public static double distance(int rgb1, int rgb2) {
        int r1 = rgb1 >> 16 & 0xFF;
        int g1 = rgb1 >> 8 & 0xFF;
        int b1 = rgb1 & 0xFF;

        LOG.info("r1: " + r1 + " g1: " + g1 + " b1: " + b1);

        int r2 = rgb2 >> 16 & 0xFF;
        int g2 = rgb2 >> 8 & 0xFF;
        int b2 = rgb2 & 0xFF;

        LOG.info("r2: " + r2 + " g2: " + g2 + " b2: " + b2);

        return Math.sqrt(Math.pow(r2 - r1, 2) + Math.pow(g2 - g1, 2) + Math.pow(b2 - b1, 2));
    }

}