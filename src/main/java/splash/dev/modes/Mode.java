package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.util.Pixel;
import splash.dev.util.Renderable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Mode implements Renderable {

    private final Set<Pixel> pixels;
    boolean isCanvasHovered;

    public Mode() {
        pixels = new HashSet<>();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        Renderable.super.render(context, mouseX, mouseY);

        for (Pixel pixel : pixels) {

            int x = pixel.x();
            int y = pixel.y();

            context.fill(x, y, x + 4, y + 4, getColor().getRGB());
        }
    }

    public Set<Pixel> getPixels() {
        return pixels;
    }



    public void setCanvasHovered(boolean canvasHovered) {
        isCanvasHovered = canvasHovered;
    }

    public Color getColor() {
        if (this instanceof FillMode fillMode) {
            return fillMode.getColor();
        }
        return Color.WHITE;
    }
}
