package splash.dev.modes;

import net.minecraft.client.gui.DrawContext;
import splash.dev.settings.ColorSetting;
import splash.dev.settings.ModeSetting;
import splash.dev.settings.Setting;
import splash.dev.util.Pixel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FillMode extends Mode {
    private final List<Setting> settings;
    int brushSize = 4;
    private BrushMode brushMode;
    private Color brushColor;
    private boolean filling = false;

    public FillMode() {
        settings = new ArrayList<>();

        settings.add(new ModeSetting<>("Brush Mode", BrushMode.values(), BrushMode.Default)
                .onUpdate(mode -> this.brushMode = mode));
        settings.add(new ColorSetting("Brush Color", Color.WHITE)
                .onUpdate(color -> this.brushColor = color));

        this.brushMode = BrushMode.Default;
        this.brushColor = Color.WHITE;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    @Override
    public Color getColor() {
        return brushColor;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        super.render(context, mouseX, mouseY);

        if (filling && isCanvasHovered) {
            getPixels().add(new Pixel(mouseX, mouseY, brushColor, 4));
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
