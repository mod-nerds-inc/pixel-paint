package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.List;

import static splash.dev.Main.mc;

public class SettingBuilderScreen extends Screen {
    private final int boxWidth = 300, boxHeight = 50;
    private final List<Setting> settings;
    private int currentSettingIndex = 0;
    private int boxX, boxY, leftArrowX, rightArrowX, textY;


    public SettingBuilderScreen(List<Setting> settings) {
        super(Text.of("Settings"));
        this.settings = settings;
    }

    private void updateLayout() {
        int screenWidth = this.width;
        int screenHeight = this.height;

        boxX = (screenWidth - boxWidth) / 2;
        boxY = (screenHeight - boxHeight) / 2;

        int padding = 10;
        leftArrowX = boxX + padding;
        rightArrowX = boxX + boxWidth - mc.textRenderer.getWidth("<") - padding; // equal width right?
        textY = boxY + (boxHeight - mc.textRenderer.fontHeight) / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        updateLayout();

        context.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, new Color(0, 0, 0, 200).getRGB());

        if (settings.isEmpty()) return;

        Setting current = settings.get(currentSettingIndex);
        String text = current.name;

        int textWidth = mc.textRenderer.getWidth(text);
        int textX = boxX + (boxWidth - textWidth) / 2;

        context.drawText(mc.textRenderer, "<", leftArrowX, textY, -1, false);

        context.drawText(mc.textRenderer, ">", rightArrowX, textY, -1, false);

        context.drawText(mc.textRenderer, text, textX, textY, -1, false);

        Setting setting = settings.get(currentSettingIndex);
        setting.updatePosition(boxX + 15, boxY + 12); // tesing for now
        setting.render(context, mouseX, mouseY);

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return super.mouseClicked(mouseX, mouseY, button);

        updateLayout();

        if (mouseX >= leftArrowX && mouseX <= leftArrowX + mc.textRenderer.getWidth("<") &&
                mouseY >= textY && mouseY <= textY + mc.textRenderer.fontHeight) {
            currentSettingIndex = (currentSettingIndex - 1 + settings.size()) % settings.size();
            return true;
        }

        if (mouseX >= rightArrowX && mouseX <= rightArrowX + mc.textRenderer.getWidth("<") &&
                mouseY >= textY && mouseY <= textY + mc.textRenderer.fontHeight) {
            currentSettingIndex = (currentSettingIndex + 1) % settings.size();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
