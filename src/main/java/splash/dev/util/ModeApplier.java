package splash.dev.util;

import net.minecraft.client.gui.DrawContext;
import splash.dev.comps.ModeType;

import java.util.HashSet;
import java.util.Set;
public class ModeApplier {
    private static final int pixelSize = 2;
    private final Set<Pixel> pixels;
    private ModeType modeType;
    public boolean painting;
    private int lastX = -1, lastY = -1;

    public ModeApplier(ModeType modeType) {
        this.modeType = modeType;
        this.pixels = new HashSet<>();
    }

    public void setPainting(boolean painting) {
        this.painting = painting;
        if (!painting) {
            lastX = -1;
            lastY = -1;
            if (modeType == ModeType.ERASER) {
                pixels.clear();
            }
        }
    }

    public void render(DrawContext context, ModeType modeType, boolean canvasHovered, int mouseX, int mouseY) {
        this.modeType = modeType;

        if (painting && canvasHovered) {
            int x = (mouseX / pixelSize) * pixelSize;
            int y = (mouseY / pixelSize) * pixelSize;

            if (lastX != -1 && lastY != -1) {
                drawLine(lastX, lastY, x, y);
            } else {
                applyPixel(x, y);
            }

            lastX = x;
            lastY = y;
        }

        for (Pixel pixel : pixels) {
            context.fill(pixel.x, pixel.y, pixel.x + pixelSize, pixel.y + pixelSize, -1);
        }
    }

    private void applyPixel(int x, int y) {
        Pixel pixel = new Pixel(x, y);
        if (modeType == ModeType.PENCIL) {
            pixels.add(pixel);
        } else if (modeType == ModeType.ERASER) {
            pixels.remove(pixel);
        }
    }

    private void drawLine(int startX, int startY, int endX, int endY) { // basic interpolation thanks to some guy on dc
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        int stepX = Integer.compare(endX, startX);
        int stepY = Integer.compare(endY, startY);
        int close = deltaX - deltaY;

        int currentX = startX;
        int currentY = startY;

        while (true) {
            applyPixel(currentX, currentY);

            if (currentX == endX && currentY == endY) break;

            int error2 = 2 * close;

            if (error2 > -deltaY) {
                close -= deltaY;
                currentX += stepX;
            }

            if (error2 < deltaX) {
                close += deltaX;
                currentY += stepY;
            }
        }
    }

    private record Pixel(int x, int y) {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pixel(int x1, int y1) && x1 == x && y1 == y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }
}
