package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.settings.ColorSetting;
import splash.dev.settings.ModeSetting;
import splash.dev.settings.Setting;
import splash.dev.util.Pixel;
import splash.dev.util.PixelHolder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PencilMode extends Mode {
    private final List<Setting> settings;
    int brushSize = 4;
    boolean filling;
    public PencilMode() {

        settings = new ArrayList<>();

        settings.add(new ModeSetting<>("Brush Mode", BrushMode.values(), BrushMode.Default).onUpdate(mode -> StoredInfo.brushMode = mode));
        settings.add(new ColorSetting("Brush Color", Color.WHITE).onUpdate(color -> StoredInfo.brushColor = color));

    }



    @Override
    public List<Setting> getSettings() {
        return settings;
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        super.render(context, mouseX, mouseY);
        if (filling && isCanvasHovered) {
            PixelHolder.getInstance().addPixel(new Pixel(getCanvasX(mouseX),getCanvasY(mouseY), StoredInfo.brushColor, brushSize));
        }
    }


    @Override
    public void mouseClicked(int button, int x, int y) {
        super.mouseClicked(button, x, y);
        if (button == 0 && isCanvasHovered) {
            filling = true;
        }
    }

    @Override
    public Color getColor() {
        return StoredInfo.brushColor;
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        if (button == 0) {
            filling = false;
        }
        super.mouseRelease(button, x, y);
    }



    public enum BrushMode {
        Default, Precise, Pixel
    }


}
