package splash.dev.comps;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import splash.dev.util.Renderer2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskComp {
    private final int iconSize, iconSpacing, iconPadding, height;
    List<Mode> modes = new ArrayList<>();


    public TaskComp() {

        iconSize = 15; /*this makes customizing so easy <3 im genius*/
        iconSpacing = 13;
        iconPadding = 20;
        height = 20;

        for (ModeType value : ModeType.values()) {
            register(new Mode(value));
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        int windowWidth = context.getScaledWindowWidth();
        int windowHeight = context.getScaledWindowHeight();

        int iconsWidth = modes.size() * iconSize + (modes.size() - 1) * iconSpacing;
        int totalWidth = iconsWidth + iconPadding * 2;

        int x1 = (windowWidth - totalWidth) / 2;
        int y1 = windowHeight - height - 8;
        int x2 = x1 + totalWidth;
        int y2 = y1 + height;

        context.fill(x1, y1, x2, y2, new Color(0, 0, 0, 199).getRGB());
        context.drawBorder(x1, y1, totalWidth, height, new Color(206, 206, 206, 197).getRGB());

        int startX = x1 + (totalWidth - iconsWidth) / 2;
        int iconY = y1 + (height - iconSize) / 2;

        for (int i = 0; i < modes.size(); i++) {
            Mode mode = modes.get(i);
            int iconX = startX + i * (iconSize + iconSpacing);
            Renderer2D.renderTexture(mode.getTexture(), context, iconX, iconY, iconSize, Color.WHITE);
        }
    }

    public void register(Mode mode) {
        if (!modes.contains(mode)) modes.add(mode);
    }


    public enum ModeType {
        PENCIL,
        COLOR,
        ERASER,
        FILL,
        SELECT
    }

    public record Mode(ModeType modeType) {
        public Identifier getTexture() {
            return Identifier.of("pixel-paint", "texture/" + modeType.toString().toLowerCase(Locale.ROOT) + ".png");
        }
    }
}
