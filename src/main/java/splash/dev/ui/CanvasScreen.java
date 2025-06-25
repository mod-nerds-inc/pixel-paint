package splash.dev.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CanvasScreen extends Screen {
    public CanvasScreen() {
        super(Text.of("canvas.screen"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.fill(1,1,50,255,-1); // here we go again (=
        super.render(context, mouseX, mouseY, delta);
    }
}
