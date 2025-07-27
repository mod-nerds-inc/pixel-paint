package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static splash.dev.Main.mc;

public class ModeSetting<T extends Enum<T>> extends Setting {
    private final List<T> modes;
    private T currentMode;
    private boolean expanded = false;
    private Consumer<T> onChange;

    private final int boxWidth = 220;
    private final int boxHeight = 23;
    private final int optionHeight = 20;

    public ModeSetting(String name, T[] enumValues, T defaultMode) {
        super(name);
        this.modes = Arrays.asList(enumValues);
        this.currentMode = defaultMode != null ? defaultMode : enumValues[0];
    }

    public ModeSetting<?> onUpdate(Consumer<T> onChange) {
        this.onChange = onChange;
        return this;
    }


    public void setValue(T value) {
        if (value != currentMode) {
            this.currentMode = value;
            if (onChange != null) onChange.accept(currentMode);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        super.render(context, mouseX, mouseY);

        int visibleOptions = expanded ? (modes.size() - 1) : 0;
        int totalHeight = boxHeight + visibleOptions * optionHeight;
        updateSize(boxWidth, totalHeight);

        context.fill(x, y, x + boxWidth, y + boxHeight, new Color(40, 40, 40, 180).getRGB());

        String text = "[" + currentMode.name() + "]";
        context.drawText(mc.textRenderer, text, x + 8, y + 7, Color.WHITE.getRGB(), false);

        String arrow = expanded ? "▲" : "▼";
        context.drawText(mc.textRenderer, arrow, x + boxWidth - 14, y + 8, Color.WHITE.getRGB(), false);

        if (expanded) {
            int drawY = y + boxHeight;

            for (T mode : modes) {
                if (mode == currentMode) continue;

                context.fill(x, drawY, x + boxWidth, drawY + optionHeight, new Color(0, 153, 204, 180).getRGB());
                context.drawText(mc.textRenderer, mode.name(), x + 8, drawY + 5, Color.WHITE.getRGB(), false);

                drawY += optionHeight;
            }
        }
    }

    @Override
    public void mouseRelease(int button, int mouseX, int mouseY) {
        if (isInside(mouseX, mouseY, x, y, boxWidth, boxHeight)) {
            expanded = !expanded;
            return;
        }

        if (expanded) {
            int drawY = y + boxHeight;

            for (T mode : modes) {
                if (mode == currentMode) continue;

                if (isInside(mouseX, mouseY, x, drawY, boxWidth, optionHeight)) {
                    setValue(mode);
                    expanded = false;
                    break;
                }

                drawY += optionHeight;
            }
        } else {
            expanded = false;
        }

        super.mouseRelease(button, mouseX, mouseY);
    }

    private boolean isInside(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
