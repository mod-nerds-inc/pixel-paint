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
        if (this == obj) return true;
        if (!(obj instanceof Pixel other)) return false;
        return other.x == x &&
                other.y == y &&
                other.size == size &&
                other.color.equals(color);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        result = 31 * result + Integer.hashCode(size);
        result = 31 * result + color.hashCode();
        return result;
    }

}
