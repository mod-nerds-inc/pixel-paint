package splash.dev;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import splash.dev.ui.CanvasScreen;
import splash.dev.util.PixelHolder;

public class Main implements ModInitializer {
    public static final String MOD_ID = "pixel-paint";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MinecraftClient mc = MinecraftClient.getInstance();
    private static CanvasScreen canvasScreen;

    public static CanvasScreen requestCanvas() {
        mc.setScreen(canvasScreen);
        return canvasScreen;
    }

    @Override
    public void onInitialize() {
        KeyBinding recordGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Canvas",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                MOD_ID
        ));

        canvasScreen = new CanvasScreen();


        HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
            drawContext.drawText(mc.textRenderer, String.valueOf(PixelHolder.getInstance().getPixels().size()), 5,5,-1,true);
        });
        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> {
            if (mc.currentScreen == null && mc.world != null) {

                if (recordGui.wasPressed() && !(mc.currentScreen instanceof CanvasScreen)) {
                    requestCanvas();
                }
            }
        });
    }
}