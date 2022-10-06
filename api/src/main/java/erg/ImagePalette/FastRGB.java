
package erg.ImagePalette;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class FastRGB
{

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
}