package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.comps.SliderComp;
import splash.dev.settings.Setting;
import splash.dev.settings.SliderSetting;
import splash.dev.util.Pixel;
import splash.dev.util.PixelHolder;

import java.util.ArrayList;
import java.util.List;

public class EraserMode extends Mode {
    private final List<Setting> settings;
    private final SliderSetting sizeSetting;

    public EraserMode() {
        settings = new ArrayList<>();

        List<SliderComp> sliders = new ArrayList<>();
        sliders.add(new SliderComp("Eraser Size", 1, 20, 4));

        sizeSetting = new SliderSetting("Eraser Size", sliders);
        settings.add(sizeSetting);
    }

    boolean clear;

    @Override
    public List<Setting> getSettings() {
        return settings;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        if (clear) {
            int localX = getCanvasX(mouseX);
            int localY = getCanvasY(mouseY);

            int eraserSize = (int) sizeSetting.getSliders().getFirst().getValue();

            List<Pixel> toRemove = new ArrayList<>();

            for (Pixel pixel : PixelHolder.getInstance().getPixels().values()) {
                if (localX >= pixel.x() - eraserSize / 2 &&
                        localX < pixel.x() + pixel.size() + eraserSize / 2 &&
                        localY >= pixel.y() - eraserSize / 2 &&
                        localY < pixel.y() + pixel.size() + eraserSize / 2) {
                    toRemove.add(pixel);
                }
            }
            for (Pixel pixel : toRemove) {
                PixelHolder.getInstance().clearPixelAt(pixel.x(), pixel.y());
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


    public SliderSetting getSizeSetting() {
        return sizeSetting;
    }

    @Override
    public int getHandScale() {
        return (int) (super.getHandScale() * getSizeSetting().getSliders().getFirst().getValue());
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        if (button == 0) {
            clear = false;
        }
        super.mouseRelease(button, x, y);
    }
}
