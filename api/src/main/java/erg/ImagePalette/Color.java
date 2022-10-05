package erg.ImagePalette;

public class Color {

    public float r;
    public float g;
    public float b;
    public String hex;

    public float h;
    public float s;
    public float v;

    public float c;
    public float m;
    public float y;
    public float k;

    enum ColorFormat {
        RGB,
        HSV,
        CMYK
    }

    private Color(float rhc, float gsm, float bvy, float k, ColorFormat format) {
        
        if(format == ColorFormat.RGB) {
            setRGB(rhc, gsm, bvy);
            setHSV(rgbToHsv(rhc, gsm, bvy));
            setCMYK(rgbToCmyk(rhc, gsm, bvy));
        } else if(format == ColorFormat.HSV) {
            setHSV(rhc, gsm, bvy);
            setRGB(rgbToHsv(rhc, gsm, bvy));
            setCMYK(rgbToCmyk(r, g, b));
        } else if(format == ColorFormat.CMYK) {
            setCMYK(rhc, gsm, bvy, k);
            setRGB(cmykToRgb(rhc, gsm, bvy, k));
            setHSV(rgbToHsv(r, g, b));
        }
    }

    private void setRGB(float... rgb) {
        this.r = rgb[0];
        this.g = rgb[1];
        this.b = rgb[2];
        this.hex = String.format("#%02x%02x%02x", r, g, b);
    }

    private void setHSV(float... hsv) {
        this.h = hsv[0];
        this.s = hsv[1];
        this.v = hsv[2];
    }
    
    private void setCMYK(float... cmyk) {
        this.c = cmyk[0];
        this.m = cmyk[1];
        this.y = cmyk[2];
        this.k = cmyk[3];
    }

    public Color fromRGB(float r, float g, float b) {
        return new Color(r, g, b, 0, ColorFormat.RGB);
    }

    public Color fromHSV(float h, float s, float v) {
        return new Color(h, s, v, 0, ColorFormat.HSV);
    }

    public Color fromCMYK(float c, float m, float y, float k) {
        return new Color(c, m, y, k, ColorFormat.CMYK);
    }

    public float[] rgbToHsv(float r, float g, float b) {
        r = r / 255f;
        g = g / 255f;
        b = b / 255f;
 
        float cmax = Math.max(r, Math.max(g, b));
        float cmin = Math.min(r, Math.min(g, b));
        float diff = cmax - cmin;
        float h = -1, s = -1;
         
        if (cmax == cmin)
            h = 0;
 
        else if (cmax == r)
            h = (60 * ((g - b) / diff) + 360) % 360;
 
        else if (cmax == g)
            h = (60 * ((b - r) / diff) + 120) % 360;
 
        else if (cmax == b)
            h = (60 * ((r - g) / diff) + 240) % 360;
 
        if (cmax == 0)
            s = 0;
        else
            s = (diff / cmax) * 100;
 
        float v = cmax * 100;
        return new float[] {h, s, v};
    }

    public float[] rgbToCmyk(float r, float g, float b) {
        r = r / 255f;
        g = g / 255f;
        b = b / 255f;
        float k = 1 - Math.max(r, Math.max(g, b));
        float c = (1 - r - k) / (1 - k);
        float m = (1 - g - k) / (1 - k);
        float y = (1 - b - k) / (1 - k);
        return new float[] {c, m, y, k};
    }

    public  float[] hsvToRgb(float h, float s, float v) {
        s = s/100;
        v = s/100;
        float C = s * v;
        float X = C * (1 - Math.abs(((h / 60f) % 2) - 1));
        float m = v - C;
        float r, g, b;
        if(h >= 0 && h < 60) {
            r = C;
            g = X;
            b = 0;
        }
        else if(h >= 60 && h < 120) {
            r = X;
            g = C;
            b = 0;
        }
        else if(h >= 120 && h < 180) {
            r = 0;
            g = C;
            b = X;
        }
        else if(h >= 180 && h < 240) {
            r = 0;
            g = X;
            b = C;
        }
        else if(h >= 240 && h < 300) {
            r = X;
            g = 0;
            b = C;
        }
        else{
            r = C;
            g = 0;
            b = X;
        }
        r = (r + m) * 255;
        g = (g + m) * 255;
        b = (b + m) * 255;
        return new float[] {r, g, b};
    }

    public float[] cmykToRgb(float c, float m, float y, float k) {
        float r = 255 * (1 - c) * (1 - k);
        float g = 255 * (1 - m) * (1 - k);
        float b = 255 * (1 - y) * (1 - k);
        return new float[] {r, g, b};
    }
    
}
