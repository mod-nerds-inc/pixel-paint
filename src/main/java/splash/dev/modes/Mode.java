package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.settings.Setting;
import splash.dev.util.Pixel;
import splash.dev.util.PixelHolder;
import splash.dev.util.Renderable;

import java.awt.*;
import java.util.List;

public class Mode implements Renderable {

    boolean isCanvasHovered;
    int canvasX, canvasY;

    public Mode() {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        Renderable.super.render(context, mouseX, mouseY);

        for (Pixel pixel : PixelHolder.getInstance().getPixels()) {
            int drawX = canvasX + pixel.x();
            int drawY = canvasY + pixel.y();

            context.fill(drawX, drawY, drawX + pixel.size(), drawY + pixel.size(), pixel.color().getRGB());
        }

    }

    public void updateCoords(int x, int y) {
        this.canvasX = x;
        this.canvasY = y;
    }

    public int getCanvasX(int mouseX) {
        return mouseX - canvasX;
    }

    public int getCanvasY(int mouseY) {
        return mouseY - canvasY;
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
