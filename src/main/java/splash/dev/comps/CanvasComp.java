package splash.dev.comps;

import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import splash.dev.modes.Mode;
import splash.dev.util.Renderable;
import splash.dev.util.Renderer2D;

import java.awt.*;

import static splash.dev.Main.mc;

public class CanvasComp implements Renderable {
    int width, height;
    TaskComp taskComp;
    Mode mode;

    private int x1, y1, x2, y2;

    public CanvasComp(TaskComp taskComp) {
        height = 300;
        width = 500;
        this.taskComp = taskComp;
        mode = taskComp.getCurrentMode().getMode();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void mouseClicked(int button, int x, int y) {
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            int localX = x - x1;
            int localY = y - y1;
            mode.mouseClicked(button, localX, localY);
        }
        Renderable.super.mouseClicked(button, x, y);
    }

    @Override
    public void mouseRelease(int button, int x, int y) {
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            int localX = x - x1;
            int localY = y - y1;
            mode.mouseRelease(button, localX, localY);
        }
        Renderable.super.mouseRelease(button, x, y);
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY) {
        int windowWidth = context.getScaledWindowWidth();
        int windowHeight = context.getScaledWindowHeight();

        x1 = (windowWidth - width) / 2;
        y1 = (windowHeight - height) / 2;
        x2 = x1 + width;
        y2 = y1 + height;

        boolean hovered = mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
        mode.setCanvasHovered(hovered);

        context.fill(x1, y1, x2, y2, new Color(1, 1, 1, 100).getRGB());
        context.drawBorder(x1, y1, width, height, new Color(65, 65, 65, 255).getRGB());



        long window = mc.getWindow().getHandle();

        if (hovered) {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
            Renderer2D.renderTexture(taskComp.getCurrentMode().getTexture(), context, mouseX, mouseY - 9, 15, mode.getColor());
        } else {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }

        mode.render(context, mouseX, mouseY);
        Renderable.super.render(context, mouseX, mouseY);
    }
}
