package splash.dev.comps;

import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class SliderComp {
    private final float minValue;
    private final float maxValue;
    String name;
    private float value;
    private int width, height, x, y;
    private boolean dragging = false;

    public SliderComp(String name, float minValue, float maxValue, float defaultValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = defaultValue;
        this.width = 100;
        this.height = 20;
        this.name = name;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY) {

        context.fill(x, y, x + width, y + height, -11184811);

        int knobX = (int) (x + ((value - minValue) / (maxValue - minValue)) * width);
        context.fill(x, y, knobX, y + height, -16733441);

        context.fill(knobX - 3, y, knobX + 3, y + height, Color.BLUE.getRGB());

        if (dragging) {
            updateValue(mouseX);
        }
    }

    public void mouseClicked(int button, int mouseX, int mouseY) {
        if (button == 0 && isInside(mouseX, mouseY)) {
            updateValue(mouseX);
            dragging = true;
        }
    }

    public void mouseReleased(int button, int mouseX, int mouseY) {
        if (button == 0) {
            dragging = false;
        }
    }


    private void updateValue(int mouseX) {
        float relative = (float) (mouseX - x) / width;
        relative = Math.max(0, Math.min(1, relative));
        this.value = minValue + relative * (maxValue - minValue);
    }

    private boolean isInside(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public float getValue() {
        return value;
    }
}
