package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class CanvasScreen extends Screen {
    public CanvasScreen() {
        super(Text.of("canvas.screen"));
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int panelWidth = 400;
        int panelHeight = 180;

        int centerY = (this.height / 2) - (panelHeight / 2);
        int centerX = 0;

        context.fill(
                centerX,
                centerY,
                centerX + panelWidth,
                centerY + panelHeight,
                new Color(35, 35, 35, 255).getRGB()
        );

        int boxHeight = 120;
        int boxTop = centerY + (panelHeight / 2) - (boxHeight / 2);
        int boxRight = centerX + panelWidth;
        int boxBottom = boxTop + boxHeight;

        int lineColor = new Color(150, 150, 150, 255).getRGB();

        int numLines = 4;
        for (int i = 1; i <= numLines; i++) {
            int y = boxTop + (i * boxHeight / (numLines + 1));

            context.fill(
                    centerX + 4,
                    y,
                    boxRight - 4,
                    y + 1,
                    lineColor
            );

            int tickHeight = 5;
            context.fill(centerX + 4, y - tickHeight / 2, centerX + 5, y + tickHeight / 2, lineColor);
            context.fill(boxRight - 5, y - tickHeight / 2, boxRight - 4, y + tickHeight / 2, lineColor);
        }

        int midX = centerX + panelWidth / 2;
        context.fill(
                midX,
                boxTop + 4,
                midX + 1,
                boxBottom - 4,
                lineColor
        );

        int capWidth = 8;
        context.fill(
                midX - capWidth / 2,
                boxBottom - 4,
                midX + capWidth / 2,
                boxBottom - 3,
                lineColor
        );

        context.fill(
                midX - capWidth / 2,
                boxTop + 3,
                midX + capWidth / 2,
                boxTop + 4,
                lineColor
        );

        super.render(context, mouseX, mouseY, delta);
    }


}
