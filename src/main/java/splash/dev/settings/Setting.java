package splash.dev.settings;

import splash.dev.util.Renderable;

public abstract class Setting implements Renderable {
    String name;
    int width, height;
    int x, y;
    public Setting(String name) {
        this.name = name;
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
