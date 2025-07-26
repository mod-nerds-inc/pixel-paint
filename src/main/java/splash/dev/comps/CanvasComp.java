package splash.dev.comps;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;
import splash.dev.util.ModeApplier;
import splash.dev.util.Renderer2D;

import java.awt.*;

public class CanvasComp {
    int width, height;
    TaskComp taskComp;
    ModeApplier modeApplier;

    public CanvasComp(TaskComp taskComp) {
        height = 300;
        width = 500;
        this.taskComp = taskComp;
        modeApplier = new ModeApplier(taskComp.getCurrentMode().modeType());
    }

    public void mouseClicked(int button, int x, int y) {
        if (button != 0) return;
        modeApplier.setPainting(true);
    }
    public void release(int button, int x, int y) {
        if (button != 0) return;
        modeApplier.setPainting(false);

    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        int windowWidth = context.getScaledWindowWidth();
        int windowHeight = context.getScaledWindowHeight();

        int x1 = (windowWidth - width) / 2;
        int y1 = (windowHeight - height) / 2;
        int x2 = x1 + width;
        int y2 = y1 + height;

        context.fill(x1, y1, x2, y2, new Color(1, 1, 1, 100).getRGB());
        context.drawBorder(x1, y1, width, height, new Color(65, 65, 65, 255).getRGB());

        updateCursor(context, mouseX, mouseY, windowWidth, windowHeight);
        modeApplier.render(context, taskComp.getCurrentMode().modeType(), isCanvasHovered(mouseX, mouseY, windowWidth, windowHeight), mouseX, mouseY);


    }

    public void updateCursor(DrawContext context, int mouseX, int mouseY, int windowWidth, int windowHeight) {
        long window = MinecraftClient.getInstance().getWindow().getHandle();

        if (isCanvasHovered(mouseX, mouseY, windowWidth, windowHeight)) {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
            Renderer2D.renderTexture(taskComp.getCurrentMode().getTexture(), context, mouseX, mouseY - 9, 15, Color.WHITE);
        } else {
            GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }
    }


    public boolean isCanvasHovered(int mouseX, int mouseY, int windowWidth, int windowHeight) {
        int x1 = (windowWidth - width) / 2;
        int y1 = (windowHeight - height) / 2;
        int x2 = x1 + width;
        int y2 = y1 + height;

        return mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
    }
}
