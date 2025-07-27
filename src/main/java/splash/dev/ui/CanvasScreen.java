package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import splash.dev.comps.CanvasComp;
import splash.dev.comps.SliderComp;
import splash.dev.comps.TaskComp;

public class CanvasScreen extends Screen {
    TaskComp taskComp;
    CanvasComp canvasComp;
    public CanvasScreen() {
        super(Text.of("canvas.screen"));
        taskComp = new TaskComp();
        canvasComp = new CanvasComp(taskComp);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        canvasComp.mouseClicked(button, (int) mouseX, (int) mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }



    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        taskComp.render(context,mouseX,mouseY);
        canvasComp.render(context,mouseX,mouseY);


    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        taskComp.mouseReleased(button, (int) mouseX, (int) mouseY);
        canvasComp.mouseRelease(button, (int) mouseX, (int) mouseY);

        return super.mouseReleased(mouseX, mouseY, button);
    }


}
