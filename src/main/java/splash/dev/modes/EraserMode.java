package splash.dev.modes;

public class EraserMode extends Mode{
    public EraserMode() {

    }
    @Override
    public void mouseClicked(int button, int x, int y) {
        super.mouseClicked(button, x, y);
        if (button == 0 && isCanvasHovered) {
            getPixels().clear();
        }
    }
}
