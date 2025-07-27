package splash.dev.settings;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import static splash.dev.Main.mc;

public class SettingBuilderScreen extends Screen {
    private final int boxWidth = 250, boxHeight = 30;
    private final List<Setting> settings;
    private final Consumer<List<Setting>> onCloseCallback;
    private int currentSettingIndex = 0;
    private int boxX, boxY, leftArrowX, rightArrowX, textY;

    public SettingBuilderScreen(List<Setting> settings, Consumer<List<Setting>> onClose) {
        super(Text.of("Settings"));
        this.settings = settings;
        this.onCloseCallback = onClose;
    }

    @Override
    public void close() {
        if (onCloseCallback != null) {
            onCloseCallback.accept(settings);
        }
        super.close();
    }

    private void updateLayout() {
        int screenWidth = this.width;
        int screenHeight = this.height;

        int settingHeight = settings.isEmpty() ? 0 : settings.get(currentSettingIndex).height;
        int totalHeight = boxHeight + settingHeight;

        boxX = (screenWidth - boxWidth) / 2;
        boxY = (screenHeight - totalHeight) / 2;

        int padding = 10;
        leftArrowX = boxX + padding;
        rightArrowX = boxX + boxWidth - mc.textRenderer.getWidth("<") - padding;
        textY = boxY + (boxHeight - mc.textRenderer.fontHeight) / 2;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        updateLayout();

        context.fillGradient(boxX, boxY, boxX + boxWidth, boxY + boxHeight,
                new Color(28, 28, 28, 255).getRGB(), new Color(24, 24, 24, 255).getRGB());

        int borderColor = new Color(112, 112, 112, 163).getRGB();
        context.fill(boxX, boxY, boxX + boxWidth, boxY + 1, borderColor);
        context.fill(boxX, boxY, boxX + 1, boxY + boxHeight, borderColor);
        context.fill(boxX + boxWidth - 1, boxY, boxX + boxWidth, boxY + boxHeight, borderColor);

        if (settings.isEmpty()) return;

        Setting current = settings.get(currentSettingIndex);
        String text = current.name;

        int textWidth = mc.textRenderer.getWidth(text);
        int textX = boxX + (boxWidth - textWidth) / 2;

        context.drawText(mc.textRenderer, "<", leftArrowX, textY, -1, false);
        context.drawText(mc.textRenderer, ">", rightArrowX, textY, -1, false);
        context.drawText(mc.textRenderer, text, textX, textY, -1, false);

        int settingX = boxX + (boxWidth / 2) - (current.width / 2);
        int settingY = boxY + boxHeight;

        current.updatePosition(settingX, settingY);
        current.render(context, mouseX, mouseY);

        if (settingX < boxX) {
            context.fill(settingX, settingY + current.height - 1, settingX + current.width, settingY + current.height, borderColor);
            context.fill(settingX, settingY, settingX + 1, settingY + current.height, borderColor);
            context.fill(settingX + current.width - 1, settingY, settingX + current.width, settingY + current.height, borderColor);

            context.fill(settingX + current.width, settingY, boxX + boxWidth - 1, settingY + 1, borderColor);
            context.fill(settingX, settingY, boxX + 1, settingY + 1, borderColor);
        } else {
            context.fill(boxX, settingY, boxX + boxWidth, settingY + 1, borderColor);
        }
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        settings.get(currentSettingIndex).mouseRelease(button, (int) mouseX, (int) mouseY);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        settings.get(currentSettingIndex).mouseClicked(button, (int) mouseX, (int) mouseY);
        if (button != 0) return super.mouseClicked(mouseX, mouseY, button);

        updateLayout();

        if (mouseX >= leftArrowX && mouseX <= leftArrowX + mc.textRenderer.getWidth("<") &&
                mouseY >= textY && mouseY <= textY + mc.textRenderer.fontHeight) {
            currentSettingIndex = (currentSettingIndex - 1 + settings.size()) % settings.size();
            return true;
        }

        if (mouseX >= rightArrowX && mouseX <= rightArrowX + mc.textRenderer.getWidth(">") &&
                mouseY >= textY && mouseY <= textY + mc.textRenderer.fontHeight) {
            currentSettingIndex = (currentSettingIndex + 1) % settings.size();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
