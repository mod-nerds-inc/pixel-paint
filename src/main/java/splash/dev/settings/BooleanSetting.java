package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;

public class BooleanSetting extends Setting {

    boolean active;
    int w=400,h=40;

    public BooleanSetting(String name, boolean active) {
        super(name);
        this.active = active;
        updateSize(w, h);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        context.fill(x, y, x + w, y + h, active ? -1 : -6954223);
        super.render(context, mouseX, mouseY);
    }

}
