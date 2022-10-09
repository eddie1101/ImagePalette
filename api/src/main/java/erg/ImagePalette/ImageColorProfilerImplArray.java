package erg.ImagePalette;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageColorProfilerImplArray implements ImageColorProfiler {

    @Override
    public ColorProfile getColorProfile(int[] image, double aggregationThreshold) {
        HashMap<Integer, Integer> colorCount = new HashMap<>();
        for(int i = 0; i < image.length; i++) {
            if(!colorCount.containsKey(image[i])) {
                colorCount.put(image[i], 1);
            } else {
                colorCount.put(image[i], colorCount.get(image[i]) + 1);
            }
        }
        int l = colorCount.keySet().size();
        Integer[] keys = new Integer[l];
        Iterator<Integer> it = colorCount.keySet().iterator();
        int k = 0;
        while(it.hasNext()) {
            keys[k] = it.next();
            k++;
        }
        colorCount.keySet();
        int[] distinctColors = new int[l];
        int[] distinctColorPortion = new int[l];
        for(int i = 0; i < l; i++) {
            distinctColors[i] = keys[i];
            distinctColorPortion[i] = colorCount.get(keys[i]);
        }

        return new ColorProfile(distinctColors, distinctColorPortion, aggregationThreshold);

    }

}
