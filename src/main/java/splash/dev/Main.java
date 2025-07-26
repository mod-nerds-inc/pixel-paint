package splash.dev;

import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import splash.dev.settings.BooleanSetting;
import splash.dev.settings.ModeSetting;
import splash.dev.settings.Setting;
import splash.dev.settings.SettingBuilderScreen;
import splash.dev.ui.CanvasScreen;

import java.util.ArrayList;
import java.util.List;

public class Main implements ModInitializer {
    public static final String MOD_ID = "pixel-paint";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final EventBus EVENT_BUS = new EventBus();
    public static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        KeyBinding recordGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Canvas",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                MOD_ID
        ));

        List<Setting> settings = new ArrayList<>();

        settings.add(new BooleanSetting("Smooth",true));
        settings.add(new ModeSetting("Mode", new String[]{"Test", "Lol"}));

        SettingBuilderScreen settingBuilderScreen = new SettingBuilderScreen(settings);

        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> {
            if (mc.currentScreen == null && mc.world != null) {

                if (recordGui.wasPressed() && !(mc.currentScreen instanceof CanvasScreen)) {
                    mc.setScreen(settingBuilderScreen);
                }
            }
        });
    }
}