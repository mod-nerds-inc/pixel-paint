package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.util.PixelHolder;
import splash.dev.util.Pixel;

public class EraserMode extends Mode {
    public EraserMode() {
    }

    boolean clear;

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        if (clear) {
            int localX = getCanvasX(mouseX);
            int localY = getCanvasY(mouseY);

            Pixel pixelToClear = null;
            for (Pixel pixel : PixelHolder.getInstance().getPixels().values()) {
                if (localX >= pixel.x() && localX < pixel.x() + pixel.size() &&
                        localY >= pixel.y() && localY < pixel.y() + pixel.size()) {
                    pixelToClear = pixel;
                    break;
                }
            }

            if (pixelToClear != null) {
                PixelHolder.getInstance().clearPixelAt(pixelToClear.x(), pixelToClear.y());
            }
        }
        super.render(context, mouseX, mouseY);
    }

    @Override
    public void mouseClicked(int button, int x, int y) {
        super.mouseClicked(button, x, y);
        if (button == 0 && isCanvasHovered) {
            clear = true;
        }
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        if (button == 0) {
            clear = false;
        }
        super.mouseRelease(button, x, y);
    }
}
