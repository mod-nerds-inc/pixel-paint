package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import splash.dev.comps.TaskComp;

public class CanvasScreen extends Screen {
    TaskComp taskComp;
    public CanvasScreen() {
        super(Text.of("canvas.screen"));
        taskComp = new TaskComp();
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        taskComp.render(context,mouseX,mouseY);

    }


}
