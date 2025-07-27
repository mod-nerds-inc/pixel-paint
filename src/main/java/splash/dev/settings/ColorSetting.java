package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;
import splash.dev.comps.SliderComp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ColorSetting extends Setting {

    private final List<SliderComp> sliders = new ArrayList<>();
    private Consumer<Color> onChange;

    public ColorSetting(String name, Color defaultValue) {
        super(name);

        sliders.add(new SliderComp("Red", 0, 255, defaultValue.getRed()));
        sliders.add(new SliderComp("Green", 0, 255, defaultValue.getGreen()));
        sliders.add(new SliderComp("Blue", 0, 255, defaultValue.getBlue()));
        sliders.add(new SliderComp("Alpha", 0, 255, defaultValue.getAlpha()));
    }

    public ColorSetting onUpdate(Consumer<Color> onChange) {
        this.onChange = onChange;
        return this;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        super.render(context, mouseX, mouseY);

        int x = this.x;
        int y = this.y + 5;

        int sliderSpacing = 22;
        for (SliderComp slider : sliders) {
            slider.setPosition(x + 10, y);
            slider.render(context, mouseX, mouseY);
            y += sliderSpacing;
        }

        int boxSize = 80;
        int colorBoxX = x + 140;
        int colorBoxY = this.y + 8;
        Color currentColor = getColor();

        context.fill(colorBoxX, colorBoxY, colorBoxX + boxSize, colorBoxY + boxSize, currentColor.getRGB());
        context.drawBorder(colorBoxX, colorBoxY, boxSize, boxSize, currentColor.darker().getRGB());
        updateSize(250, sliders.size() * sliderSpacing + 10);
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        sliders.forEach(slider -> slider.mouseReleased(button, x, y));
        callOnChange();
        super.mouseRelease(button, x, y);
    }

    @Override
    public void mouseClicked(int button, int x, int y) {
        sliders.forEach(slider -> slider.mouseClicked(button, x, y));
        callOnChange();
        super.mouseClicked(button, x, y);
    }

    public Color getColor() {
        int r = (int) sliders.get(0).getValue();
        int g = (int) sliders.get(1).getValue();
        int b = (int) sliders.get(2).getValue();
        int a = (int) sliders.get(3).getValue();
        return new Color(r, g, b, a);
    }

    private void callOnChange() {
        if (onChange != null) {
            onChange.accept(getColor());
        }
    }
}
