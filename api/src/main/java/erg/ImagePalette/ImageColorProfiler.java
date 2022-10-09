package erg.ImagePalette;

@FunctionalInterface
public interface ImageColorProfiler {
    
    ColorProfile getColorProfile(int[] image, double aggregationThreshold);

}
