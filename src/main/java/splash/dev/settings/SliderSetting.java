package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;
import splash.dev.comps.SliderComp;

import java.util.List;
import java.util.function.Consumer;

public class SliderSetting extends Setting {

    private final List<SliderComp> sliders;
    private Consumer<Void> onChange;

    public SliderSetting(String name, List<SliderComp> sliders) {
        super(name);
        this.sliders = sliders;
    }

    public List<SliderComp> getSliders() {
        return sliders;
    }

    public SliderSetting onUpdate(Consumer<Void> onChange) {
        this.onChange = onChange;
        return this;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        super.render(context, mouseX, mouseY);

        int x = this.x + 10;
        int y = this.y + 5;
        int sliderSpacing = 22;

        for (SliderComp slider : sliders) {
            slider.setPosition(x, y);
            slider.render(context, mouseX, mouseY);
            y += sliderSpacing;
        }

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

    private void callOnChange() {
        if (onChange != null) {
            onChange.accept(null);
        }
    }
}
