package splash.dev.comps;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import splash.dev.modes.*;
import splash.dev.settings.SettingBuilderScreen;
import splash.dev.util.Renderer2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static splash.dev.Main.mc;

public class TaskComp {
    private final int iconSize, iconSpacing, iconPadding, height;
    private final List<TaskbarButton> taskbarButtons = new ArrayList<>();
    private final List<Rectangle> iconBounds = new ArrayList<>();
    private TaskbarButton currentTaskbarButton;

    private int lastX1, lastY1, lastTotalWidth;

    public TaskComp() {
        iconSize = 15;
        iconSpacing = 13;
        iconPadding = 20;
        height = 25;

        for (ModeType value : ModeType.values()) {
            register(new TaskbarButton(value));
        }
        currentTaskbarButton = taskbarButtons.getFirst();
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        int windowWidth = context.getScaledWindowWidth();
        int windowHeight = context.getScaledWindowHeight();

        int iconsWidth = taskbarButtons.size() * iconSize + (taskbarButtons.size() - 1) * iconSpacing;
        int totalWidth = iconsWidth + iconPadding * 2;

        int x1 = (windowWidth - totalWidth) / 2;
        int y1 = windowHeight - height - 8;
        int startX = x1 + (totalWidth - iconsWidth) / 2;
        int iconY = y1 + (height - iconSize) / 2;

        lastX1 = x1;
        lastY1 = y1;
        lastTotalWidth = totalWidth;

        iconBounds.clear();

        context.fill(x1, y1, x1 + totalWidth, y1 + height, new Color(0, 0, 0, 199).getRGB());
        context.drawBorder(x1, y1, totalWidth, height, new Color(206, 206, 206, 197).getRGB());

        for (int i = 0; i < taskbarButtons.size(); i++) {
            TaskbarButton taskbarButton = taskbarButtons.get(i);
            int iconX = startX + i * (iconSize + iconSpacing);

            Rectangle bounds = new Rectangle(iconX, iconY, iconSize, iconSize);
            iconBounds.add(bounds);

            boolean isHovered = bounds.contains(mouseX, mouseY);

            if (isHovered) {
                context.fill(iconX - 2, iconY - 2, iconX + iconSize + 2, iconY + iconSize + 2,
                        new Color(255, 255, 255, 50).getRGB());
            }

            Renderer2D.renderTexture(taskbarButton.getTexture(), context, iconX, iconY, iconSize, Color.WHITE);
        }
    }

    public void mouseReleased(int button, int mouseX, int mouseY) {

        for (int i = 0; i < iconBounds.size(); i++) {
            Rectangle bounds = iconBounds.get(i);
            if (bounds.contains(mouseX, mouseY)) {
                switch (button) {
                    case 0 -> currentTaskbarButton = taskbarButtons.get(i);
                    case 1 -> {
                        TaskbarButton taskbarButton = taskbarButtons.get(i);
                        mc.setScreen(new SettingBuilderScreen(taskbarButton.getMode().getSettings()));
                    }
                }
                break;
            }
        }
    }

    public void register(TaskbarButton taskbarButton) {
        if (!taskbarButtons.contains(taskbarButton)) taskbarButtons.add(taskbarButton);
    }

    public TaskbarButton getCurrentMode() {
        return currentTaskbarButton;
    }

    public List<Rectangle> getIconBounds() {
        return iconBounds;
    }

    public Rectangle getTaskbarBounds() {
        return new Rectangle(lastX1, lastY1, lastTotalWidth, height);
    }



    public record TaskbarButton(ModeType modeType) {
        public Identifier getTexture() {
            return Identifier.of("pixel-paint", "texture/" + modeType.toString().toLowerCase(Locale.ROOT) + ".png");
        }
        public Mode getMode(){
            return switch (modeType){
                case PENCIL -> new PencilMode();
                case COLOR -> new ColorMode();
                case ERASER -> new EraserMode();
                case FILL -> new FillMode();
                case SELECT -> new SelectMode();
            };
        }

    }
}
