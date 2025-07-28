package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.settings.Setting;
import splash.dev.util.Pixel;
import splash.dev.util.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Mode implements Renderable {

    private final List<Pixel> pixels;
    boolean isCanvasHovered;

    public Mode() {
        pixels = new ArrayList<>();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        Renderable.super.render(context, mouseX, mouseY);

        for (Pixel pixel : pixels) {

            int x = pixel.x();
            int y = pixel.y();

            context.fill(x, y, x + pixel.size(), y + pixel.size(), pixel.color().getRGB());
        }
    }

    public List<Pixel> getPixels() {
        return pixels;
    }


    public List<Setting> getSettings(){ /*will be overriden so null plz*/
        return null;
    }
    public void setCanvasHovered(boolean canvasHovered) {
        isCanvasHovered = canvasHovered;
    }

    public Color getColor() {
        if (this instanceof PencilMode pencilMode) {
            return pencilMode.getColor();
        }
        return Color.WHITE;
    }
}
