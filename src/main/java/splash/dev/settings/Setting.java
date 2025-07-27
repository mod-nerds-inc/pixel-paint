package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;
import splash.dev.util.Renderable;

import java.awt.*;

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

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        context.fill(x, y, x + width, y + height,  new Color(24, 24, 24, 255).getRGB());

        Renderable.super.render(context, mouseX, mouseY);
    }
}
