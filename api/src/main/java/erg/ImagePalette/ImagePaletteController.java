package erg.ImagePalette;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class ImagePaletteController {

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
}