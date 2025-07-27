package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.settings.Setting;
import splash.dev.util.Pixel;
import splash.dev.util.Renderable;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
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

            System.out.println("got color " + pixel.color());
            context.fill(x, y, x + pixel.size(), y + pixel.size(), pixel.color().getRGB());
        }
    }

    public Set<Pixel> getPixels() {
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
