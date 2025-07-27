package splash.dev.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import java.awt.*;

public class Renderer2D {

    public static void renderTexture(Identifier texture, DrawContext context, int x, int y, int scale, Color color) {
        RenderSystem.setShaderColor(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        RenderSystem.setShaderTexture(0, texture);
        context.drawTexture(RenderLayer::getGuiTextured, texture, x, y, 0, 0, scale, scale, scale, scale);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

}
