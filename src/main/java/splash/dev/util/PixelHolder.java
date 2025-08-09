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

    public void addPixel(Pixel pixel) {
        System.out.println("added pixel at x" +pixel.x() + " y "+pixel.y());
        pixels.add(pixel);
    }


    public List<Pixel> getPixels() {
        return pixels;
    }

    public void clearPixelAt(int x, int y) {
        boolean found = false;
        for (Pixel pixel : pixels) {
            if (pixel.x() == x && pixel.y() == y) {
                System.out.println("Removing pixel at: " + x + "," + y);
                found = true;
                pixels.remove(pixel);
                break;
            }
        }
        if (!found) {
            System.out.println("No pixel found at: " + x + "," + y);
        }
    }


    public void clearPixels() {
        pixels.clear();
    }
}
