package splash.dev.settings;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class SettingBuilderScreen extends Screen {
    List<Setting> settings;

    public SettingBuilderScreen(List<Setting> settings) {
        super(Text.of("settings.".concat(String.valueOf(settings.size()))));
        this.settings = settings;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        for (int i = 0; i < settings.size(); i++) {
            context.drawText(MinecraftClient.getInstance().textRenderer, settings.get(i).name, 5, 2 + (i * MinecraftClient.getInstance().textRenderer.fontHeight)
                    , -1, true);
        }
        super.render(context, mouseX, mouseY, delta);
    }
}
