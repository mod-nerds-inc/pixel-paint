package splash.dev.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PixelHolder { // eventaully hadf to become a class to get serialized lol
    private static final PixelHolder INSTANCE = new PixelHolder();

    private final Map<String, Pixel> pixels = new HashMap<>();


    private PixelHolder() {
    }

    public static PixelHolder getInstance() {
        return INSTANCE;
    }

    public void addPixel(Pixel pixel) {
        String key = pixel.x() + "," + pixel.y();
        if (pixels.containsKey(key)) {
            return;
        }
        pixels.put(key, pixel);
    }


    public Map<String, Pixel> getPixels() {
        return pixels;
    }

    public void clearPixelAt(int x, int y) {
        String key = x + "," + y;
        pixels.remove(key);
    }

    public void clearPixels() {
        pixels.clear();
    }
}
