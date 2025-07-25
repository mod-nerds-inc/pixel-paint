package splash.dev.comps;

import net.minecraft.util.Identifier;

import java.util.Locale;

public record Mode(ModeType modeType) {
        public Identifier getTexture() {
            return Identifier.of("pixel-paint", "texture/" + modeType.toString().toLowerCase(Locale.ROOT) + ".png");
        }
    }