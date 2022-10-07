package erg.ImagePalette;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.logging.Logger;

import javax.imageio.IIOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class ImagePaletteController {

    private static final Logger LOG = Logger.getLogger(ImagePaletteController.class.getName());

    public ImagePaletteController() {

    }

    @GetMapping("/{file}")
    public ResponseEntity<ImageData> getImageData(@PathVariable("file") String path) {
        try {
            return new ResponseEntity<ImageData>(ImageAnalyzer.getColorFrequency(path), HttpStatus.OK);
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/distance")
    public ResponseEntity<Integer> getColorDistance(@RequestParam(name = "r1", defaultValue = "0") int r1,
                                                    @RequestParam(name = "g1", defaultValue = "0") int g1,
                                                    @RequestParam(name = "b1", defaultValue = "0") int b1,
                                                    @RequestParam(name = "r2", defaultValue = "0") int r2,
                                                    @RequestParam(name = "g2", defaultValue = "0") int g2,
                                                    @RequestParam(name = "b2", defaultValue = "0") int b2) {
        int rgb1 = (int) 0xff << 24;
        rgb1 += (r1 & 0xff) << 16;
        rgb1 += (g1 & 0xff) << 8;
        rgb1 += b1 & 0xff;

        LOG.info("rgb1: " + Integer.toBinaryString(rgb1));
        
        int rgb2 = 0xff << 24;
        rgb2 += (r2 & 0xff) << 16;
        rgb2 += (g2 & 0xff) << 8;
        rgb2 += b2 & 0xff;

        LOG.info("rgb2: " + Integer.toBinaryString(rgb2));

        return new ResponseEntity<Integer>(FastRGB.distance(rgb1, rgb2), HttpStatus.OK);
    }
}