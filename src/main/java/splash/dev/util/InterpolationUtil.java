package splash.dev.util;

import java.util.function.BiConsumer;

public class InterpolationUtil {
    ///  bombclattt my fav topic 🤤
    public static void begin(int startX, int startY, int endX, int endY, BiConsumer<Integer, Integer> pointConsumer) { // https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm shoutout to my boy Bresenham your a sigma
        int absXDifference = Math.abs(endX - startX);
        int absYDifference = Math.abs(endY - startY);
        int stepX = startX < endX ? 1 : -1;
        int stepY = startY < endY ? 1 : -1;
        int fix = absXDifference - absYDifference;

        int currentX = startX;
        int currentY = startY;

        while (true) {
            pointConsumer.accept(currentX, currentY);

            if (currentX == endX && currentY == endY) break;

            int fixDouble = 2 * fix;

            if (fixDouble > -absYDifference) {
                fix -= absYDifference;
                currentX += stepX;
            }
            if (fixDouble < absXDifference) {
                fix += absXDifference;
                currentY += stepY;
            }
        }
    }

}
