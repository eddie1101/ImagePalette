package erg.ImagePalette;

import java.util.TreeSet;
import java.util.Comparator;

public class ImageColorProfilerImplTree extends TreeSet<Integer> implements ImageColorProfiler {

    static class RGBComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer arg0, Integer arg1) {
            //Compare based on distance to white
            if(arg0 == arg1) return 0;
            double arg0Dist = FastRGB.distance(arg0, 0xff << 24),
                   arg1Dist = FastRGB.distance(arg1, 0xff << 24);
            if(arg0Dist < arg1Dist) 
                return -1;
            else
                return 1;
        }
    }

    public ImageColorProfilerImplTree() {
        super(new RGBComparator());
    }

    @Override
    public ColorProfile getColorProfile(int[] image, double aggregationThreshold) {
        return null;
    }

}