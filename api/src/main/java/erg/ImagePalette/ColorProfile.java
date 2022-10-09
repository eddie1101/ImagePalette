package erg.ImagePalette;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorProfile {
    
    @JsonProperty private int[] distinctColors;
    @JsonProperty private int[] distinctColorPortion;
    @JsonProperty private int numDistinctColors;
    @JsonProperty private double colorAggregation;

    //Assume that distinctColors and distinctColorPortion are parallel arrays
    public ColorProfile(int[] distinctColors, int[] distinctColorPortion, double colorAggregation) {
        this.distinctColors = distinctColors;
        this.distinctColorPortion = distinctColorPortion;
        this.numDistinctColors = distinctColors.length; 
        this.colorAggregation = colorAggregation;
    }

    public void setDistinctColors(int[] distinctColors) {
        this.distinctColors = distinctColors;
    }

    public void setColorPortion(int[] distinctColorPortion) {
        this.distinctColorPortion = distinctColorPortion;
    }

    public void setColorAggregation(double colorAggregation) {
        this.colorAggregation = colorAggregation;
    }

}
