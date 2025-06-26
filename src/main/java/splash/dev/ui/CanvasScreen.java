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
        int panelWidth = 36;
        int panelHeight = 190;

        int centerX = 0;
        int centerY = (this.height / 2) - (panelHeight / 2);

        int bg = new Color(35, 35, 35, 255).getRGB();

        context.fill(
                centerX,
                centerY,
                centerX + panelWidth,
                centerY + panelHeight,
               bg
        );

        int squaresCount = 4;
        int squareHeight = 20;

        int totalSquaresHeight = squaresCount * squareHeight;

        int startY = centerY + (panelHeight - totalSquaresHeight) / 2;

        int squareX = 0;

        int c = new Color(255, 255, 255, 134).getRGB();

        int hoveredSquare = -1;
        if (mouseX >= squareX && mouseX <= squareX + panelWidth &&
                mouseY >= startY && mouseY <= startY + squaresCount * squareHeight) {
            hoveredSquare = (mouseY - startY) / squareHeight;
        }

        for (int i = 0; i < squaresCount; i++) {
            int squareY = startY + squareHeight * i;
            int halfWidth = panelWidth / 2;

            int leftFillColor = bg;
            int rightFillColor = bg;

            if (i == hoveredSquare) {
                if (mouseX < squareX + halfWidth) {
                    leftFillColor = new Color(255, 0, 0, 134).getRGB();
                } else {
                    rightFillColor = new Color(255, 0, 0, 134).getRGB();
                }
            }

            context.fill(
                    squareX,
                    squareY,
                    squareX + halfWidth,
                    squareY + squareHeight,
                    leftFillColor
            );

            context.fill(
                    squareX + halfWidth,
                    squareY,
                    squareX + panelWidth,
                    squareY + squareHeight,
                    rightFillColor
            );

            context.fill(squareX, squareY, squareX + panelWidth, squareY + 1, c);

            int centerLineX = squareX + halfWidth - 1;
            context.fill(
                    centerLineX,
                    squareY-15,
                    centerLineX + 1,
                    squareY + squareHeight+15,
                    c
            );
        }


        super.render(context, mouseX, mouseY, delta);
    }







}
