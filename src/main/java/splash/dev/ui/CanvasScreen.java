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
        // coding from a ms paint pic is god tier
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

//        int white = Color.WHITE.getRGB();
        int red = Color.WHITE.getRGB();
        int black =Color.WHITE.getRGB();

        for (int i = 0; i < squaresCount; i++) {
            int squareY = startY + squareHeight * i;

            context.fill(
                    squareX,
                    squareY,
                    squareX + panelWidth,
                    squareY + squareHeight,
                    bg
            );

            context.fill(squareX, squareY, squareX + panelWidth, squareY + 1, red);
//            context.fill(squareX, squareY + squareHeight - 1, squareX + squareWidth, squareY + squareHeight, red);
           /* context.fill(squareX, squareY, squareX + 1, squareY + squareHeight, red);
            context.fill(squareX + squareWidth - 1, squareY, squareX + squareWidth, squareY + squareHeight, red);
*/
            int centerLineX = (squareX + panelWidth / 2)-1;
            context.fill(
                    centerLineX,
                    squareY-15,
                    centerLineX + 1,
                    squareY + squareHeight+5,
                    black
            );
        }

        super.render(context, mouseX, mouseY, delta);
    }







}
