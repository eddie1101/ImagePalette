package erg.ImagePalette;

import java.util.HashMap;
import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageData {

    @JsonProperty int numPixels;
    @JsonProperty int numDistinctColors;
    // @JsonProperty int[] r, g, b, a;
    @JsonProperty int[] colorSet;
    @JsonProperty int numColorPixels[];

    //Assume inputs are same length
    public ImageData(int[] pixels) {
        this.numPixels = pixels.length;
        // this.r = new int[numPixels];
        // this.g = new int[numPixels];
        // this.b = new int[numPixels];
        // this.a = new int[numPixels];
        HashMap<Integer, Integer> colorCount = new HashMap<>();
        for(int i = 0; i < numPixels; i++) {
            // r[i] = pixels[i] >> 24 & 0xFF;
            // g[i] = pixels[i] >> 16 & 0xFF;
            // b[i] = pixels[i] >> 8 & 0xFF;
            // a[i] = pixels[i] & 0xFF;
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
}
