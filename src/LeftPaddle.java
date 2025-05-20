import java.awt.event.KeyEvent;
import java.util.Set;

import static utils.Constants.*;

public class LeftPaddle extends Sprite{
    private int upKey;
    private int downKey;
    private double dx;
    private double dy;

    public LeftPaddle() {
        super(LEFT_PADDLE_IMAGE_PATH, 30, 70, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void tick() {
        dx = 0;
        pos.translate((int) dx, (int) dy);
        pos.y = Math.clamp(pos.y, 0, BOARD_HEIGHT - PADDLE_HEIGHT);
    }

    public void handleActiveKeys(Set<Integer> activeKeyCodes) {
        dy = 0;
        if (activeKeyCodes.contains(KeyEvent.VK_W)) {
            dy -= PADDLE_SPEED;
        } else if (activeKeyCodes.contains(KeyEvent.VK_S)) {
            dy += PADDLE_SPEED;
        }
    }
}
