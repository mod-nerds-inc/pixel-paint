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
            for (Pixel pixel : PixelHolder.getInstance().getPixels()) {
                // Check if the pixel overlaps the coordinates (considering pixel size)
                if (localX >= pixel.x() && localX < pixel.x() + pixel.size() &&
                        localY >= pixel.y() && localY < pixel.y() + pixel.size()) {
                    pixelToClear = pixel;
                    break; // Found pixel to clear, exit loop early
                }
            }

            if (pixelToClear != null) {
                System.out.println("[EraserMode] Clearing pixel at: (" + pixelToClear.x() + ", " + pixelToClear.y() + ")");
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
            System.out.println("[EraserMode] Started clearing");
        }
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        if (button == 0) {
            clear = false;
            System.out.println("[EraserMode] Stopped clearing");
        }
        super.mouseRelease(button, x, y);
    }
}
