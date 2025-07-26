package splash.dev.util;

import net.minecraft.client.gui.DrawContext;

public interface Renderable {
    default void render(DrawContext context, int mouseX, int mouseY) {
    }


    default void mouseClicked(int button, int x, int y) {
    }

    default void mouseRelease(int button, int x, int y) {

    }
}
