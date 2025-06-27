package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class CanvasScreen extends Screen {
    private final int panelWidth = 36;
    private final int panelHeight = 190;
    private final int rows = 3;
    private final int cellHeight = 20;
    private final int cellWidth = panelWidth / 2;

    public CanvasScreen() {
        super(Text.of("canvas.screen"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = 0;
        int centerY = (this.height / 2) - (panelHeight / 2);

        int bg = new Color(35, 35, 35, 255).getRGB();
        int highlightColor = new Color(255, 0, 0, 134).getRGB();
        int borderColor = new Color(255, 255, 255, 134).getRGB();

        context.fill(centerX, centerY, centerX + panelWidth, centerY + panelHeight, bg);

        int totalHeight = rows * cellHeight;
        int startY = centerY + (panelHeight - totalHeight) / 2;

        int hoveredRow = -1;
        boolean left = false;

        if (mouseX >= centerX && mouseX <= centerX + panelWidth &&
                mouseY >= startY && mouseY <= startY + totalHeight) {
            hoveredRow = (mouseY - startY) / cellHeight;
            left = mouseX < centerX + cellWidth;
        }

        for (int row = 0; row < rows; row++) {
            int y = startY + row * cellHeight;

            int leftColor = (hoveredRow == row && left) ? highlightColor : bg;
            int rightColor = (hoveredRow == row && !left) ? highlightColor : bg;

            context.fill(centerX, y, centerX + cellWidth, y + cellHeight, leftColor);
            context.fill(centerX + cellWidth, y, centerX + panelWidth, y + cellHeight, rightColor);
        }

        for (int row = 0; row <= rows; row++) {
            int y = startY + row * cellHeight;
            int centerDividerX = centerX + cellWidth - 1;

            context.fill(centerX, y, centerDividerX, y + 1, borderColor);
            context.fill(centerDividerX + 1, y, centerX + panelWidth, y + 1, borderColor);
        }

        int verticalLineBottom = startY + totalHeight+1;
        int centerLineX = centerX + cellWidth - 1;

        context.fill(centerLineX, startY, centerLineX + 1, verticalLineBottom, borderColor);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int centerX = 0;
        int centerY = (this.height / 2) - (panelHeight / 2);
        int totalHeight = rows * cellHeight;
        int startY = centerY + (panelHeight - totalHeight) / 2;

        if (mouseX >= centerX && mouseX <= centerX + panelWidth &&
                mouseY >= startY && mouseY <= startY + totalHeight) {

            int row = (int) ((mouseY - startY) / cellHeight);
            boolean left = mouseX < centerX + cellWidth;

            String cell = switch (row) {
                case 0 -> left ? "a1" : "a2";
                case 1 -> left ? "b1" : "b2";
                case 2 -> left ? "c1" : "c2";
                default -> "nonebroskeis";
            };

            System.out.println("u cliekd" + cell);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
