package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;

public class BooleanSetting extends Setting {

    boolean active;

    public BooleanSetting(String name, boolean active) {
        super(name);
        this.active = active;
        updateSize(15, 15);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        context.fill(x, y, x + 15, y + 15, active ? -1 : -6954223);
        super.render(context, mouseX, mouseY);
    }

}
