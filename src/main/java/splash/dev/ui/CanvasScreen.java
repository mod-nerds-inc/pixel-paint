package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import splash.dev.comps.CanvasComp;
import splash.dev.comps.TaskComp;
import splash.dev.util.PixelHolder;

import static splash.dev.Main.mc;

public class CanvasScreen extends Screen {
    TaskComp taskComp;
    CanvasComp canvasComp;
    boolean showDebug;
    public CanvasScreen() {
        super(Text.of("canvas.screen"));
        taskComp = new TaskComp();
        canvasComp = new CanvasComp(taskComp);
    }

    public CanvasComp getCanvasComp() {
        return canvasComp;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        canvasComp.mouseClicked(button, (int) mouseX, (int) mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }


    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_F3) showDebug = !showDebug; // we stealing mojangs ideas wit this one!!
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        if (showDebug) {
            int y = 1;
            int offset = mc.textRenderer.fontHeight + 2;

            String[] lines = new String[]{
                    "Pixels: " + PixelHolder.getInstance().getPixels().size(),
                    "Size: " + canvasComp.getWidth() + " x " + canvasComp.getHeight(),
            };

            for (String line : lines) {
                context.drawText(mc.textRenderer, line, 1, y, -1, true);
                y += offset;
            }
        }

        canvasComp.render(context,mouseX,mouseY);
        taskComp.render(context,mouseX,mouseY);


    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        taskComp.mouseReleased(button, (int) mouseX, (int) mouseY);
        canvasComp.mouseRelease(button, (int) mouseX, (int) mouseY);

        return super.mouseReleased(mouseX, mouseY, button);
    }


}
