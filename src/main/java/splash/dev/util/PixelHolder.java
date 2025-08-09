package splash.dev.util;

import java.util.ArrayList;
import java.util.List;

public class PixelHolder { // eventaully hadf to become a class to get serialized lol
    private static final PixelHolder INSTANCE = new PixelHolder();

    private final List<Pixel> pixels = new ArrayList<>();

    private PixelHolder() {
    }

    public static PixelHolder getInstance() {
        return INSTANCE;
    }

    public void addPixel(Pixel newPixel) {
        if (pixels.contains(newPixel)) { // crazy how i didnt optimize it beforel like dis lOL
            return;
        }
        pixels.add(newPixel);
    }



    public List<Pixel> getPixels() {
        return pixels;
    }

    public void clearPixelAt(int x, int y) {
        for (Pixel pixel : pixels) {
            if (pixel.x() == x && pixel.y() == y) {
                pixels.remove(pixel);
                break;
            }
        }
    }


    public void clearPixels() {
        pixels.clear();
    }
}
