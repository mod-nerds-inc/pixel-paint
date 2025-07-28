package splash.dev.util;

import java.awt.*;
import java.util.Objects;

public class Pixel {
    private final int x;
    private final int y;
    private final Color color;
    private final int size;

    public Pixel(int x, int y, Color color, int size) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Color color() {
        return color;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pixel other &&
                other.x == x && other.y == y &&
                other.size == size &&
                other.color.equals(color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color, size);
    }

}
